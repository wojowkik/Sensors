package com.pl.wrb.r2.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsManagement implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor sensorLight, sensorAmbientTemperature;
    private float valLight, valTemperature;
    private String errorNote = "";

    SensorsManagement(SensorManager sensorManager)
    {
        this.sensorManager = sensorManager;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            // Success! There's a light sensor.
            sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        } else {
            // Failure! No light sensor.
            errorNote += "NO LIGHT SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            sensorAmbientTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        } else {
            errorNote += "NO AMBIENT TEMPERATURE SENSOR\n";
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
        if(event.sensor == sensorLight) valLight = event.values[0];
        if(event.sensor == sensorAmbientTemperature) valTemperature = event.values[0];
        // Do something with this sensor value.
    }

    String getSensorsInfo()
    {
        return "Light: " + valLight + "\nAmbient Temperature: " + valTemperature + "\nErrors:\n" + errorNote;
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
