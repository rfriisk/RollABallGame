package com.tec.rollaballgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    SensorManager senMan;

    FrameLayout frmLayout;
    Ball BB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frmLayout = findViewById(R.id.frmLayout);

        senMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        BB = new Ball(this);

        frmLayout.addView(BB);
    }



    @Override
    protected void onResume() {
        super.onResume();

        senMan.registerListener(BB, senMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        senMan.unregisterListener(BB);
    }

}