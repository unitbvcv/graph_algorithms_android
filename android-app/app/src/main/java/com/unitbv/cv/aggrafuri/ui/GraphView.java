package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.math.MathUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

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

//        if(clickPositionX >= 0 && clickPositionY >= 0) {
//            canvas.drawCircle(clickPositionX, clickPositionY, NODE_CIRCLE_RADIUS, paint);
//            clickPositionX = -1;
//            clickPositionY = -1;
//        }

        int k = 30;

        Random generator = new Random();
        int x1 = generator.nextInt(500);
        int y1 = generator.nextInt(500);
        int x2 = generator.nextInt(500);
        int y2 = generator.nextInt(500);

        double alpha, alpha1, alpha2;


        // Caz orizontal
        if (y1 == y2)
        {
            if (x2 > x1)
                alpha = 0.0f;
            else
                alpha = Math.PI;
        }
        else
        {
            if (x1 == x2) // caz vertical
            {
                if (y2 > y1)
                    alpha = Math.PI / 2;
                else
                    alpha = 3 * Math.PI / 2;
            }
            else // caz oblic
                alpha = Math.atan(  ((double)(y2 - y1)) / (x2 - x1) );
        }

        alpha1 = alpha + Math.PI / 6;
        alpha2 = alpha - Math.PI / 6;

        int x3 = (int) (x2 - k * Math.cos(alpha1));
        int y3 = (int) (y2 - k * Math.sin(alpha1));

        int x4 = (int) (x2 - k * Math.cos(alpha2));
        int y4 = (int) (y2 - k * Math.sin(alpha2));

        // First line from (x1, y1) to (x2, y2)
        canvas.drawLine(x1, y1, x2, y2, paint);

        // Second line from (x2, y2) to (x3, y3)
        canvas.drawLine(x2, y2, x3, y3, paint);

        // Third line from (x2, y2) to (x4, y4)
        canvas.drawLine(x2, y2, x4, y4, paint);
    }

    public void clear() {
        canvasNeedsClearing = true;
        invalidate();
    }
}
