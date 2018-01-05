package com.unitbv.cv.aggrafuri.utils;

public class Math {
    /**
     *
     * @return The angle between the given line and Ox in radians, in the interval [0, 2pi].
     */
    public static double angleOfLineToOx(float x1, float y1, float x2, float y2) {
        return (java.lang.Math.atan2(y2-y1, x2-x1) + 2 * java.lang.Math.PI) % (2 * java.lang.Math.PI);
    }
}
