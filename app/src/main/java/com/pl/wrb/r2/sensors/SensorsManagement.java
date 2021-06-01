package com.pl.wrb.r2.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsManagement implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor sensorLight;
    private float valLight;

    SensorsManagement(SensorManager sensorManager)
    {
        this.sensorManager = sensorManager;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            // Success! There's a light sensor.
            sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        } else {
            // Failure! No light sensor.
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        if(event.sensor == sensorLight)
        {
            valLight = event.values[0];
        }
        // Do something with this sensor value.
    }

    String getSensorsInfo()
    {
        return "Light: "+valLight;
    }

    void setSensorsListeners()
    {
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
    void unsetSensorsListeners()
    {
        sensorManager.unregisterListener(this);
    }
}
