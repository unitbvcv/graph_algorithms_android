package com.unitbv.cv.aggrafuri.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.unitbv.cv.aggrafuri.utils.Math;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class GraphView extends View {
    GraphView_ViewModel view_viewModel = null;

    // drawing model
    ArrayList<ArcParams> arcs = new ArrayList<>();
    ArrayList<LineParams> lines = new ArrayList<>();
    ArrayList<TextParams> texts = new ArrayList<>();

    private Context context;
    private Paint drawPaint = new Paint();

    public static Paint textPaint = new Paint();

    private float downTouchPositionX = -1;
    private float downTouchPositionY = -1;

    private final float TOUCH_SENSITIVITY_PIXELS = 10;

    public final static int NODE_COLOR_A = 255;
    public final static int NODE_COLOR_R = 0;
    public final static int NODE_COLOR_G = 0;
    public final static int NODE_COLOR_B = 0;

    public final static float NODE_CIRCLE_RADIUS = 70;
    public final static float FONT_SIZE_WEIGHT = 50.0f;

    public final static float NODE_STROKE_WIDTH = 3.0f;
    public final static float WEIGHT_SPACING = 15.0f;

    public final static int BACKGROUND_COLOR_R = 255;
    public final static int BACKGROUND_COLOR_G = 255;
    public final static int BACKGROUND_COLOR_B = 255;

    public final static float ARROW_LEG_LENGTH = 50;
    public final static double ARROW_LEG_ANGLE = java.lang.Math.PI / 6;

    private boolean canvasNeedsClearing = false;

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        drawPaint.setARGB(NODE_COLOR_A, NODE_COLOR_R, NODE_COLOR_G, NODE_COLOR_B);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(NODE_STROKE_WIDTH);
        drawPaint.setAntiAlias(true);

        textPaint.setTextSize(FONT_SIZE_WEIGHT);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    downTouchPositionX = motionEvent.getX();
                    downTouchPositionY = motionEvent.getY();
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    float upTouchPositionX = motionEvent.getX();
                    float upTouchPositionY = motionEvent.getY();

                    // check if the user didn't move the finger (ignore input when it is a gesture) +- a precision
                    if (Math.numberBetween(downTouchPositionX, upTouchPositionX - TOUCH_SENSITIVITY_PIXELS,
                            upTouchPositionX + TOUCH_SENSITIVITY_PIXELS, true, true)
                            && Math.numberBetween(downTouchPositionY, upTouchPositionY - TOUCH_SENSITIVITY_PIXELS,
                            upTouchPositionY + TOUCH_SENSITIVITY_PIXELS, true, true))
                    {
                        // we need to update the model and the view
                        view_viewModel.onViewTouch(upTouchPositionX, upTouchPositionY);
                        invalidate();
                    }
                }

                return true;
            }
        });
    }

    public void setView_viewModel(GraphView_ViewModel viewModel) {
        this.view_viewModel = viewModel;
    }

    public void setArcs(ArrayList<ArcParams> arcs) {
        this.arcs = arcs;
    }

    public void setLines(ArrayList<LineParams> lines) {
        this.lines = lines;
    }

    public void setTexts(ArrayList<TextParams> texts) {
        this.texts = texts;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(canvasNeedsClearing == true) {
            canvas.drawRGB(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B);
            canvasNeedsClearing = false;
        }

        // draw arcs
        for (ArcParams currentArc : arcs) {
            if (currentArc.isSelected()) {
                drawPaint.setColor(Color.RED);
            }

            canvas.drawArc(currentArc.getLeft(), currentArc.getTop(),
                    currentArc.getRight(), currentArc.getBottom(),
                    currentArc.getStartAngle(), currentArc.getSweepAngle(),
                    currentArc.isUseCenter(), drawPaint);

            drawPaint.setColor(Color.BLACK);
        }

        // draw lines
        for (LineParams currentLine : lines) {
            canvas.drawLine(currentLine.getStartX(), currentLine.getStartY(),
                    currentLine.getStopX(), currentLine.getStopY(), drawPaint);
        }

        // draw texts
        for (TextParams currentText : texts) {
            if (currentText.getIsWeight())
            {
                canvas.drawTextOnPath(currentText.getMessage(), currentText.getPath(),
                            0.0f, 0.0f, textPaint);
            }
            else
            {
				canvas.drawText(currentText.getMessage(), currentText.getX(),
					currentText.getY(), textPaint);
			}
        }

        /* Generate two random points and connect them (with weight).

        Random gen = new Random();

        int x1 = gen.nextInt(getWidth());
        int y1 = gen.nextInt(getHeight());
        int x2 = gen.nextInt(getWidth());
        int y2 = gen.nextInt(getHeight());

        drawPaint.setColor(Color.RED);
        canvas.drawArc(x1 - NODE_CIRCLE_RADIUS, y1 - NODE_CIRCLE_RADIUS,
                x1 + NODE_CIRCLE_RADIUS, y1 + NODE_CIRCLE_RADIUS,
                0.0f, 360.0f, false, drawPaint);
        drawPaint.setColor(Color.BLACK);

        canvas.drawArc(x2 - NODE_CIRCLE_RADIUS, y2 - NODE_CIRCLE_RADIUS,
                x2 + NODE_CIRCLE_RADIUS, y2 + NODE_CIRCLE_RADIUS,
                0.0f, 360.0f, false, drawPaint);

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

            canvas.drawLine(tan_x1, tan_y1, tan_x2, tan_y2, drawPaint);
            canvas.drawLine(tan_x2, tan_y2, C_x, C_y, drawPaint);
            canvas.drawLine(tan_x2, tan_y2, D_x, D_y, drawPaint);

            String message = "300000";

            Paint textPaint = new Paint();
            textPaint.setTextSize(FONT_SIZE_WEIGHT);

            canvas.drawTextOnPath(message, Math.generateWeightTextPath(tan_x1, tan_y1, tan_x2, tan_y2, message, textPaint), 0, 0, textPaint);
        */

        /* Generate a central point and a point on touched area and Log the touch position and the angle of the line relative to Ox.

        if (clickPositionX != -1 && clickPositionY != -1) {
            canvas.drawLine(500, 500, clickPositionX, clickPositionY, drawPaint);
            canvas.drawCircle(clickPositionX, clickPositionY, 30, drawPaint);

            double angle = Math.angleOfLineToOx(500, 500, clickPositionX, clickPositionY);

            Log.d("INFO", "TOUCH POINT: " + clickPositionX + " " + clickPositionY);
            Log.d("INFO", "ALPHA: " + angle + " (" + java.lang.Math.toDegrees(angle) + "°)");

            clickPositionX = -1;
            clickPositionY = -1;
        }
        */
    }

    public void clear() {
        canvasNeedsClearing = true;
        arcs.clear();
        lines.clear();
        texts.clear();
        invalidate();
    }

    public void promptDialog(String title, String message, final AlertDialogInterface dialogInterface) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        final View view = dialogInterface.onBuildDialog(context);
        if (view != null) {
            alertDialog.setView(view);
        }

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogInterface.onResult(view);
                dialog.dismiss();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogInterface.onCancel();
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
