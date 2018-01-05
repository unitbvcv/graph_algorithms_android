package com.unitbv.cv.aggrafuri.utils;

import java.util.Vector;

public class Math {
    /**
     *
     * @return The angle between the given line and Ox in radians, in the interval [0, 2pi].
     */
    public static double angleOfLineToOx(float x1, float y1, float x2, float y2) {
        return (java.lang.Math.atan2(y2-y1, x2-x1) + 2 * java.lang.Math.PI) % (2 * java.lang.Math.PI);
    }

    public static Vector<Float> generateCirclesConnectionPoints(float cx1, float cy1, float r1, float cx2, float cy2, float r2)
    {
        Vector<Float> result = new Vector<>(4);

        double angleOfLine = Math.angleOfLineToOx(cx1, cy1, cx2, cy2);
        double tangent_x_A, tangent_y_A, tangent_x_B, tangent_y_B;
        double alpha_A;

        if (cx1 <= cx2)
        {
            if (cy1 <= cy2) // SE
            {
                alpha_A = angleOfLine;
                tangent_x_A = cx1 + r1 * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine);
                tangent_y_A = cy1 + r1 * java.lang.Math.sin(alpha_A);

                tangent_x_B = cx2 - r2 * java.lang.Math.cos(alpha_A);
                tangent_y_B = cy2 - r2 * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine);
            }
            else // NE
            {
                alpha_A = 2 * java.lang.Math.PI - angleOfLine;
                tangent_x_A = cx1 + r1 * java.lang.Math.cos(alpha_A);
                tangent_y_A = cy1 + r1 * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine);

                tangent_x_B = cx2 + r2 * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine);
                tangent_y_B = cy2 + r2 * java.lang.Math.sin(alpha_A);
            }
        }
        else
        {
            if (cy1 <= cy2) // SV
            {
                alpha_A = java.lang.Math.PI - angleOfLine;
                tangent_x_A = cx1 + r1 * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine);
                tangent_y_A = cy1 + r1 * java.lang.Math.sin(alpha_A);

                tangent_x_B = cx2 + r2 * java.lang.Math.cos(alpha_A);
                tangent_y_B = cy2 + r2 * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine);
            }
            else // NV
            {
                alpha_A = angleOfLine - java.lang.Math.PI;
                tangent_x_A = cx1 - r1  * java.lang.Math.cos(alpha_A);
                tangent_y_A = cy1 - r1 * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine);

                tangent_x_B = cx2 + r2 * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine);
                tangent_y_B = cy2 + r2 * java.lang.Math.sin(alpha_A);
            }
        }

        result.add(Float.valueOf((float)tangent_x_A));
        result.add(Float.valueOf((float)tangent_y_A));
        result.add(Float.valueOf((float)tangent_x_B));
        result.add(Float.valueOf((float)tangent_y_B));

        return result;
    }

}
