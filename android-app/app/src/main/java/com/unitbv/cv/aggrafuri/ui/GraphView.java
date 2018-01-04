package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GraphView extends View {
    private GraphViewType graphType = GraphViewType.NOT_SET;

    private Paint paint = new Paint();

    private float clickPositionX = -1;
    private float clickPositionY = -1;

    private final int NODE_COLOR_A = 255;
    private final int NODE_COLOR_R = 0;
    private final int NODE_COLOR_G = 0;
    private final int NODE_COLOR_B = 0;
    private final float NODE_CIRCLE_RADIUS = 50;

    private final int BACKGROUND_COLOR_R = 255;
    private final int BACKGROUND_COLOR_G = 255;
    private final int BACKGROUND_COLOR_B = 255;

    private boolean canvasNeedsClearing = false;

    public enum GraphViewType {
        NOT_SET,
        UNDIRECTED,
        DIRECTED,
        UNDIRECTED_WEIGHTED,
        DIRECTED_WEIGHTED
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setARGB(NODE_COLOR_A, NODE_COLOR_R, NODE_COLOR_G, NODE_COLOR_B);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    clickPositionX = motionEvent.getX();
                    clickPositionY = motionEvent.getY();
                    invalidate();
                }
                return true;
            }
        });
    }

    public GraphViewType getGraphType() {
        return graphType;
    }

    public void setGraphType(GraphViewType graphType) {
        this.graphType = graphType;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(canvasNeedsClearing == true) {
            canvas.drawRGB(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B);
            canvasNeedsClearing = false;
        }

        if(clickPositionX >= 0 && clickPositionY >= 0) {
            canvas.drawCircle(clickPositionX, clickPositionY, NODE_CIRCLE_RADIUS, paint);
            clickPositionX = -1;
            clickPositionY = -1;
        }
    }

    public void clear() {
        canvasNeedsClearing = true;
        invalidate();
    }
}