package com.unitbv.cv.aggrafuri.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;


public class GraphView extends View {

    GraphView_ViewModel viewModel = null;

    // drawing model
    ArrayList<ArcParams> arcs = new ArrayList<>();
    ArrayList<LineParams> lines = new ArrayList<>();
    ArrayList<TextParams> texts = new ArrayList<>();

    private Context context;
    private Paint drawPaint = new Paint();

    private float clickPositionX = -1;
    private float clickPositionY = -1;

    private final int NODE_COLOR_A = 255;
    private final int NODE_COLOR_R = 0;
    private final int NODE_COLOR_G = 0;
    private final int NODE_COLOR_B = 0;

    private final static float NODE_CIRCLE_RADIUS = 50;
    private final static float NODE_STROKE_WIDTH = 3.0f;

    private final int BACKGROUND_COLOR_R = 255;
    private final int BACKGROUND_COLOR_G = 255;
    private final int BACKGROUND_COLOR_B = 255;

    private final static float ARROW_LEG_LENGTH = 50;
    private final static double ARROW_LEG_ANGLE = java.lang.Math.PI / 6;

    private boolean canvasNeedsClearing = false;

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        drawPaint.setARGB(NODE_COLOR_A, NODE_COLOR_R, NODE_COLOR_G, NODE_COLOR_B);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeWidth(NODE_STROKE_WIDTH);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    clickPositionX = motionEvent.getX();
                    clickPositionY = motionEvent.getY();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getX() == clickPositionX && motionEvent.getY() == clickPositionY) {
                        clickPositionX = motionEvent.getX();
                        clickPositionY = motionEvent.getY();
                        invalidate();
                    }
                }

                return true;
            }
        });
    }

    public void setViewModel(GraphView_ViewModel viewModel) {
        this.viewModel = viewModel;
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

        if (clickPositionX != -1 && clickPositionY != -1) {
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
                canvas.drawText(currentText.getMessage(), currentText.getX(),
                        currentText.getY(), drawPaint);
            }

//            Random gen = new Random();
//
//            int x1 = gen.nextInt(getWidth());
//            int y1 = gen.nextInt(getHeight());
//            int x2 = gen.nextInt(getWidth());
//            int y2 = gen.nextInt(getHeight());
//
//            drawPaint.setColor(Color.RED);
//            canvas.drawArc(x1 - NODE_CIRCLE_RADIUS, y1 - NODE_CIRCLE_RADIUS,
//                    x1 + NODE_CIRCLE_RADIUS, y1 + NODE_CIRCLE_RADIUS,
//                    0.0f, 360.0f, false, drawPaint);
//            drawPaint.setColor(Color.BLACK);
//
//            canvas.drawArc(x2 - NODE_CIRCLE_RADIUS, y2 - NODE_CIRCLE_RADIUS,
//                    x2 + NODE_CIRCLE_RADIUS, y2 + NODE_CIRCLE_RADIUS,
//                    0.0f, 360.0f, false, drawPaint);
//
//
////            Log.d("INFO", "x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2);
////            Log.d("INFO", "angleOfLine: " + java.lang.Math.toDegrees(angleOfLine) +
////                    " alphaA: " + java.lang.Math.toDegrees(alpha_A));
////            Log.d("INFO", "tangent_x_A: " + tangent_x_A + " tangent_y_A: " + tangent_y_A +
////            " tangent_x_B: " + tangent_x_B + " tangent_y_B: " + tangent_y_B);
//
//            Vector<Float> coord = Math.generateCirclesConnectionPoints(x1, y1, NODE_CIRCLE_RADIUS, x2, y2, NODE_CIRCLE_RADIUS);
//            float tan_x1 = coord.get(0),
//                    tan_y1 = coord.get(1),
//                    tan_x2 = coord.get(2),
//                    tan_y2 = coord.get(3);
//
//            Vector<Float> legsCoord = Math.generateArrowLegsCoordinates(tan_x1, tan_y1, tan_x2, tan_y2, ARROW_LEG_LENGTH, ARROW_LEG_ANGLE);
//            float C_x = legsCoord.get(0),
//                    C_y = legsCoord.get(1),
//                    D_x = legsCoord.get(2),
//                    D_y = legsCoord.get(3);
//
//            canvas.drawLine(tan_x1, tan_y1, tan_x2, tan_y2, drawPaint);
//            canvas.drawLine(tan_x2, tan_y2, C_x, C_y, drawPaint);
//            canvas.drawLine(tan_x2, tan_y2, D_x, D_y, drawPaint);

            clickPositionX = -1;
            clickPositionY = -1;
        }
//        canvas.drawCircle(500, 500,30, drawPaint);
//
//        if (clickPositionX != -1 && clickPositionY != -1) {
//            canvas.drawLine(500, 500, clickPositionX, clickPositionY, drawPaint);
//            canvas.drawCircle(clickPositionX, clickPositionY, 30, drawPaint);
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
