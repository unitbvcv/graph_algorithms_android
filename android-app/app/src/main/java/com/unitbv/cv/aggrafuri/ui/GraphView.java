package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.unitbv.cv.aggrafuri.utils.Math;

import java.util.Random;
import java.util.Vector;

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

    private final float ARROW_LEG_LENGTH = 50;
    private final double ARROW_LEG_ANGLE = java.lang.Math.PI / 6;

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


//            Log.d("INFO", "x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
//            Log.d("INFO", "angleOfLine: " + java.lang.Math.toDegrees(angleOfLine) +
//                    " alphaA: " + java.lang.Math.toDegrees(alpha_A));
//            Log.d("INFO", "tangent_x_A: " + tangent_x_A + " tangent_y_A: " + tangent_y_A +
//            " tangent_x_B: " + tangent_x_B + " tangent_y_B: " + tangent_y_B);

            Vector<Float> coord = Math.generateCirclesConnectionPoints(x1, y1, NODE_CIRCLE_RADIUS, x2, y2, NODE_CIRCLE_RADIUS);
            float tan_x1 = coord.get(0),
                    tan_y1 = coord.get(1),
                    tan_x2 = coord.get(2),
                    tan_y2 = coord.get(3);

            Vector<Float> legsCoord = Math.generateArrowLegsCoordinates(tan_x1, tan_y1, tan_x2, tan_y2, ARROW_LEG_LENGTH, ARROW_LEG_ANGLE);
            float C_x = legsCoord.get(0),
                    C_y = legsCoord.get(1),
                    D_x = legsCoord.get(2),
                    D_y = legsCoord.get(3);

            canvas.drawLine(tan_x1, tan_y1, tan_x2, tan_y2, paint);
            canvas.drawLine(tan_x2, tan_y2, C_x, C_y, paint);
            canvas.drawLine(tan_x2, tan_y2, D_x, D_y, paint);

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
