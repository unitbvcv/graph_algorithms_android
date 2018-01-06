package com.unitbv.cv.aggrafuri.ui;

import android.graphics.Path;
import android.view.View;

public class TextParams extends ViewParams {
    private String message = null;
    private float x = 0.0f, y = 0.0f;
    private boolean isWeight = false;
    private Path path = null;

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

    public boolean getIsWeight() {
        return isWeight;
    }

    public void setIsWeight(boolean usesPath) {
        this.isWeight = usesPath;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
