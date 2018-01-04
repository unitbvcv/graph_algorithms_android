package com.unitbv.cv.aggrafuri;

public class TextParams {
    private String message = null;
    private float x = 0.0f, y = 0.0f;

    public TextParams() {}

    public TextParams(String message, float x, float y)
    {
        this.message = message;
        this.x = x;
        this.y = y;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
