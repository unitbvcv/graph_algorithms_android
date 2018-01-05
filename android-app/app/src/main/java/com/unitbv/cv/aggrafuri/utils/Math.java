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


    public static double distanceBetweenTwoPoints(float x1, float y1, float x2, float y2)
    {
        return java.lang.Math.sqrt( (y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1) );
    }

    public static Vector<Float> rotatePoint(float cx, float cy, double angle, float x, float y)
    {
        Vector<Float> result = new Vector<>(2);

        double new_x = java.lang.Math.cos(angle) * (x - cx) - java.lang.Math.sin(angle) * (y - cy) + cx;
        double new_y = java.lang.Math.sin(angle) * (x - cx) + java.lang.Math.cos(angle) * (y - cy) + cy;

        result.add(Float.valueOf((float)new_x));
        result.add(Float.valueOf((float)new_y));
        return result;
    }

    public static Vector<Float> generateArrowLegsCoordinates(float startX, float startY, float stopX, float stopY, float legLength, double angle)
    {
        Vector<Float> result = new Vector<>(4);

        double arrowLength = Math.distanceBetweenTwoPoints(startX, startY, stopX, stopY);

        float C_x, D_x, C_y, D_y;
        C_x = D_x = (float) (startX + arrowLength - legLength * java.lang.Math.cos(angle));
        C_y = (float) (startY - legLength * java.lang.Math.sin(angle));
        D_y = (float) (startY + legLength * java.lang.Math.sin(angle));

        Vector<Float> C_rotated = Math.rotatePoint(startX, startY, Math.angleOfLineToOx(startX, startY, stopX, stopY), C_x, C_y);
        Vector<Float> D_rotated = Math.rotatePoint(startX, startY, Math.angleOfLineToOx(startX, startY, stopX, stopY), D_x, D_y);

        result.addAll(C_rotated);
        result.addAll(D_rotated);
        return result;
    }
}
