package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.math.MathUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.unitbv.cv.aggrafuri.utils.Math;

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

    private final float NODE_STROKE_WIDTH = 3.0f;

    private final int BACKGROUND_COLOR_R = 255;
    private final int BACKGROUND_COLOR_G = 255;
    private final int BACKGROUND_COLOR_B = 255;

    private final float ARROW_LEG_LENGTH = 100;

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
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(NODE_STROKE_WIDTH);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
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

        if (clickPositionX != -1 && clickPositionY != -1) {
            Random gen = new Random();

            int x1 = gen.nextInt(getWidth());
            int y1 = gen.nextInt(getHeight());
            int x2 = gen.nextInt(getWidth());
            int y2 = gen.nextInt(getHeight());

            paint.setColor(Color.RED);
            canvas.drawArc(x1 - NODE_CIRCLE_RADIUS, y1 - NODE_CIRCLE_RADIUS,
                    x1 + NODE_CIRCLE_RADIUS, y1 + NODE_CIRCLE_RADIUS,
                    0.0f, 360.0f, false, paint);
            paint.setColor(Color.BLACK);

            canvas.drawArc(x2 - NODE_CIRCLE_RADIUS, y2 - NODE_CIRCLE_RADIUS,
                    x2 + NODE_CIRCLE_RADIUS, y2 + NODE_CIRCLE_RADIUS,
                    0.0f, 360.0f, false, paint);

            double angleOfLine = Math.angleOfLineToOx(x1, y1, x2, y2);
            int tangent_x_A, tangent_y_A, tangent_x_B, tangent_y_B;
            double alpha_A;

            if (x1 <= x2)
            {
                if (y1 <= y2) // SE
                {
                    alpha_A = angleOfLine;
                    tangent_x_A = (int) (x1 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine));
                    tangent_y_A = (int) (y1 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A));

                    tangent_x_B = (int) (x2 - NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A));
                    tangent_y_B = (int) (y2 - NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine));
                }
                else // NE
                {
                    alpha_A = 2 * java.lang.Math.PI - angleOfLine;
                    tangent_x_A = (int) (x1 + NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A) );
                    tangent_y_A = (int) (y1 + NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine));

                    tangent_x_B = (int) (x2 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine));
                    tangent_y_B = (int) (y2 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A));
                }
            }
            else
            {
                if (y1 <= y2) // SV
                {
                    alpha_A = java.lang.Math.PI - angleOfLine;
                    tangent_x_A = (int) (x1 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine));
                    tangent_y_A = (int) (y1 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A));

                    tangent_x_B = (int) (x2 + NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A) );
                    tangent_y_B = (int) (y2 + NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine));
                }
                else // NV
                {
                    alpha_A = angleOfLine - java.lang.Math.PI;
                    tangent_x_A = (int) (x1 - NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A));
                    tangent_y_A = (int) (y1 - NODE_CIRCLE_RADIUS * java.lang.Math.cos(alpha_A) * java.lang.Math.tan(angleOfLine));

                    tangent_x_B = (int) (x2 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A) / java.lang.Math.tan(angleOfLine));
                    tangent_y_B = (int) (y2 + NODE_CIRCLE_RADIUS * java.lang.Math.sin(alpha_A));
                }
            }

            Log.d("INFO", "x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
            Log.d("INFO", "angleOfLine: " + java.lang.Math.toDegrees(angleOfLine) +
                    " alphaA: " + java.lang.Math.toDegrees(alpha_A));
            Log.d("INFO", "tangent_x_A: " + tangent_x_A + " tangent_y_A: " + tangent_y_A +
            " tangent_x_B: " + tangent_x_B + " tangent_y_B: " + tangent_y_B);

            canvas.drawLine(tangent_x_A, tangent_y_A, tangent_x_B, tangent_y_B, paint);

            clickPositionX = -1;
            clickPositionY = -1;
        }
//        canvas.drawCircle(500, 500,30, paint);
//
//        if (clickPositionX != -1 && clickPositionY != -1) {
//            canvas.drawLine(500, 500, clickPositionX, clickPositionY, paint);
//            canvas.drawCircle(clickPositionX, clickPositionY, 30, paint);
//
//            double angle = Math.angleOfLineToOx(500, 500, clickPositionX, clickPositionY);
//
//            Log.d("INFO", "TOUCH POINT: " + clickPositionX + " " + clickPositionY);
//            Log.d("INFO", "ALPHA: " + angle + " (" + java.lang.Math.toDegrees(angle) + "°)");
//
//            clickPositionX = -1;
//            clickPositionY = -1;
//        }
    }

    public void clear() {
        canvasNeedsClearing = true;
        invalidate();
    }
}
