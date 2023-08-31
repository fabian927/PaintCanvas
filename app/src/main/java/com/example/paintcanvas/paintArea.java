package com.example.paintcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class paintArea extends View {

    private float starX = 0;
    private float starY = 0;
    private float endX = 0;
    private float endY = 0;

    private Paint paint;
    private Canvas canvas;
    private Path path;
    private List<Path> paths;
    private List<Paint> paints;

    public paintArea (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paths = new ArrayList<>();
        paints = new ArrayList<>();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = 0;
        for (Path linea:paths){
            canvas.drawPath(linea,paints.get(i++));
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                starX = event.getX();
                starY = event.getY();

                paint = new Paint();
                paint.setStrokeWidth(15);
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.STROKE);
                paint.setAntiAlias(true);
                paints.add(paint);
                path = new Path();
                path.moveTo(starX,starY);
                paths.add(path);
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:
                int puntosRealizados = event.getHistorySize();
                for (int i=0; i<puntosRealizados; i++){
                    path.lineTo(event.getHistoricalX(i),
                            event.getHistoricalY(i));
                }
                invalidate();
        }

        return true;
    }
}
