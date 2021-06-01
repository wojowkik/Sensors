package com.pl.wrb.r2.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SensorsManagement sensorsManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorsManagement = new SensorsManagement((SensorManager) getSystemService(Context.SENSOR_SERVICE));
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorsManagement.setSensorsListeners();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        sensorsManagement.unsetSensorsListeners();
    }
}
