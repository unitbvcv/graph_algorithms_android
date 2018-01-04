package com.unitbv.cv.aggrafuri.ui;

public class LineParams {
    private float startX = 0.0f, startY = 0.0f, stopX = 0.0f, stopY = 0.0f;

    public LineParams() {}

    public LineParams(float startX, float startY, float stopX, float stopY)
    {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getStopX() {
        return stopX;
    }

    public void setStopX(float stopX) {
        this.stopX = stopX;
    }

    public float getStopY() {
        return stopY;
    }

    public void setStopY(float stopY) {
        this.stopY = stopY;
    }
}
