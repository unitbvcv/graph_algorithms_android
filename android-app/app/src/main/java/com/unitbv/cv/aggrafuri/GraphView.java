package com.unitbv.cv.aggrafuri;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GraphView extends View {
    public enum GraphViewType {
        UNDIRECTED,
        DIRECTED,
        UNDIRECTED_WEIGHTED,
        DIRECTED_WEIGHTED
    }

    private GraphViewType graphType;

    private Paint paint = new Paint();
    private float clickPositionX = -1;
    private float clickPositionY = -1;

    private final int NODE_COLOR_A = 255;
    private final int NODE_COLOR_R = 0;
    private final int NODE_COLOR_G = 0;
    private final int NODE_COLOR_B = 0;


    public GraphView(GraphViewType graphType, Context context, AttributeSet attrs) {
        super(context, attrs);

        this.graphType = graphType;

        paint.setARGB(NODE_COLOR_A, NODE_COLOR_R, NODE_COLOR_G, NODE_COLOR_B);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                clickPositionX = motionEvent.getX();
                clickPositionY = motionEvent.getY();

                invalidate();
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //viewController.viewTouched(canvas, clickPositionX, clickPositionY);
    }
}
