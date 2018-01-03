package com.unitbv.cv.aggrafuri;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Cosmin on 03-Jan-18.
 */

public class GraphView extends View {
    Paint paint = new Paint();
    Canvas oldCanvas = null;
    float clickPositionX = -1;
    float clickPositionY = -1;

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setARGB(255, 255, 0, 0);
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
        if(clickPositionX >= 0 && clickPositionY >= 0) {
            canvas.drawCircle(clickPositionX, clickPositionY, 100, paint);
        }

    }
}
