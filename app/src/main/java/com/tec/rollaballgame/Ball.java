package com.tec.rollaballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class Ball extends View implements SensorEventListener {

    float xMove = 1;
    float yMove = 1;
    int xPos = 300;
    int yPos = 300;
    Drawable drwBB;
    int drwWidth;
    int drwHeight;
    int viewWidth;
    int viewHeigth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeigth = h;
    }

    public Ball(Context context) {
        super(context);

        drwBB = ResourcesCompat.getDrawable(getResources(), R.drawable.ball_basket, null);
        drwWidth = drwBB.getIntrinsicWidth() / 10;
        drwHeight = drwBB.getIntrinsicHeight() / 10;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drwBB.setBounds(xPos, yPos, xPos + drwWidth, yPos + drwHeight);
        drwBB.draw(canvas);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        //System.out.println("Nu kører det");
            xMove = sensorEvent.values[0];
            yMove = sensorEvent.values[1];

            xPos += xMove * 10;
            yPos += yMove * 10;

            if (xPos < drwWidth) {
                xPos = drwWidth;
            } else if (xPos > viewWidth) {
                xPos = viewWidth - drwWidth;
            }

            if (yPos < drwHeight) {
                yPos = drwHeight;
            } else if (yPos > viewHeigth) {
                yPos = viewHeigth - drwHeight;
            }

        }
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
