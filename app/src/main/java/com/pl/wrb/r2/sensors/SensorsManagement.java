package com.pl.wrb.r2.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsManagement implements SensorEventListener
{
    SensorsManagement(SensorManager sensorManager)
    {

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.

        // Do something with this sensor value.
    }

    String getSensorsInfo()
    {

    }
}
