package com.pl.wrb.r2.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorsManagement sensorsManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorsManagement = new SensorsManagement((SensorManager) getSystemService(Context.SENSOR_SERVICE));
    }
}
