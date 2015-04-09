package com.example.madroid.studydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by madroid on 15-3-20.
 */
public class RippleSurface extends SurfaceView implements
        SurfaceHolder.Callback {
    private final static String TAG = "DoubleSurfaceView";
    private SurfaceHolder shd;

    public RippleSurface(Context context) {
        super(context);
        init();
    }

    public RippleSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init() {
        shd = this.getHolder();
        shd.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        width = this.getWidth();
        height = this.getHeight();

        board = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
                Bitmap.Config.ARGB_8888);
        boardCanvas = new Canvas(board);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    private int width;
    private int height;
    private Boolean isRunning = true;
    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    private Paint paint;
    private int i = 0;

    Bitmap board = null;
    Canvas boardCanvas = null;

    Thread drawThread = new Thread() {

        public void run() {
            while (isRunning) {
                long startTime = System.currentTimeMillis();
                Random random = new Random(System.currentTimeMillis());
                Canvas canvas = null;

                try {
                    paint.setColor(Color.rgb(random.nextInt(255),
                            random.nextInt(255), random.nextInt(255)));
                    paint.setStrokeWidth(4);
                    paint.setTextSize(140);

                    String text = "" + i + "";
                    boardCanvas.drawText(text, width/2, height/2, paint);
                    i++;

                    canvas = shd.lockCanvas();
                    if (canvas!=null && board!=null) {
                        canvas.drawBitmap(board, 0, 0, null);
                    }

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (shd != null && canvas != null) {
                        shd.unlockCanvasAndPost(canvas);
                    }
                    long endTime = System.currentTimeMillis();
                    Log.e(TAG, "*** 1 spend time: " + (endTime - startTime));
                }
            }
        };
    };

}
