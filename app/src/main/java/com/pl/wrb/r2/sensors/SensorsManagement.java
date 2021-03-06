package com.pl.wrb.r2.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsManagement implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor sensorLight, sensorAmbientTemperature, sensorPressure, sensorProximity, sensorHumidity,
    sensorAccelerometr, sensorGravity, sensorGyroscope, sensorAccelerometrLinear, sensorMagneticField, sensorRotationVector;
    private float valLight, valTemperature, valPressure, valProximity, valHumidity;
    private float[] valAccelerometr, valGravity, valGyroscope, valAccelerometrLinear, valMagneticField, valRotationVector;
    private String errorNote = "";

    SensorsManagement(SensorManager sensorManager)
    {
        this.sensorManager = sensorManager;

        valAccelerometr = new float[3];
        valGravity = new float[3];
        valGyroscope = new float[3];
        valAccelerometrLinear = new float[3];
        valMagneticField = new float[3];
        valRotationVector = new float[3];

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            // Success! There's a light sensor.
            sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        } else {
            // Failure! No light sensor.
            errorNote += "NO LIGHT SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            sensorAmbientTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        } else {
            errorNote += "NO AMBIENT TEMPERATURE SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
            sensorPressure= sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        } else {
            errorNote += "NO PRESSURE SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
            sensorProximity= sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        } else {
            errorNote += "NO PROXIMITY SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null){
            sensorHumidity= sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        } else {
            errorNote += "NO HUMIDITY SENSOR\n";
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensorAccelerometr= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            errorNote += "NO ACCELEROMETER SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            sensorGravity= sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        } else {
            errorNote += "NO GRAVITY SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            sensorGyroscope= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        } else {
            errorNote += "NO GYROSCOPE SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null){
            sensorAccelerometrLinear= sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        } else {
            errorNote += "NO LINEAR ACCELERATION SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            sensorMagneticField= sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        } else {
            errorNote += "NO MAGNETIC FIELD SENSOR\n";
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null){
            sensorRotationVector= sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        } else {
            errorNote += "NO ROTATION VECTOR SENSOR\n";
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
        if(event.sensor == sensorPressure) valPressure= event.values[0];
        if(event.sensor == sensorProximity) valProximity= event.values[0];
        if(event.sensor == sensorHumidity) valHumidity= event.values[0];
        //three sensor values
        if(event.sensor == sensorAccelerometr){
            valAccelerometr[0] = round3(event.values[0]);
            valAccelerometr[1] = round3(event.values[1]);
            valAccelerometr[2] = round3(event.values[2]);
        }
        if(event.sensor == sensorGravity){
            valGravity[0] = round3(event.values[0]);
            valGravity[1] = round3(event.values[1]);
            valGravity[2] = round3(event.values[2]);
        }
        if(event.sensor == sensorGyroscope){
            valGyroscope[0] = round3(event.values[0]);
            valGyroscope[1] = round3(event.values[1]);
            valGyroscope[2] = round3(event.values[2]);
        }
        if(event.sensor == sensorAccelerometrLinear){
            valAccelerometrLinear[0] = round3(event.values[0]);
            valAccelerometrLinear[1] = round3(event.values[1]);
            valAccelerometrLinear[2] = round3(event.values[2]);
        }
        if(event.sensor == sensorMagneticField){
            valMagneticField[0] = round3(event.values[0]);
            valMagneticField[1] = round3(event.values[1]);
            valMagneticField[2] = round3(event.values[2]);
        }
        if(event.sensor == sensorRotationVector){
            valRotationVector[0] = round3(event.values[0]);
            valRotationVector[1] = round3(event.values[1]);
            valRotationVector[2] = round3(event.values[2]);
        }
        // Do something with this sensor value.
    }

    String getSensorsInfo()
    {
        return "Light: " + valLight + "lx\nAmbient Temperature: " + valTemperature +
                "??C\nPressure: "+ valPressure + "hPa\nProximity: " + valProximity +
                "cm\nHumidity: " + valHumidity + "%\n" +
                getSensorDescription(valAccelerometr, "Accelerometr", "m/s2") +
                getSensorDescription(valAccelerometrLinear, "Accelerometr Linear", "m/s2") +
                getSensorDescription(valGravity, "Gravity", "m/s2") +
                getSensorDescription(valGyroscope, "Gyroscope", "rad/s") +
                getSensorDescription(valMagneticField, "Magnetic Field", "??T") +
                getSensorDescription(valRotationVector, "Rotation Vector", "") +
                "\n\nErrors:\n" + errorNote;
    }
    private String getSensorDescription(float [] value, String sensorName, String unit)
    {
        return sensorName + ":\n\t\tX: " + value[0] + unit + "\n\t\tY: " + value[1] + unit + "\n\t\tZ: " + value[2] + unit + "\n";
    }
    private float round3(float number)
    {
        number *= 1000;
        number = Math.round(number);
        number /= 1000;
        return number;
    }

    void setSensorsListeners()
    {
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAmbientTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAccelerometr, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAccelerometrLinear, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorRotationVector, SensorManager.SENSOR_DELAY_NORMAL);
    }
    void unsetSensorsListeners()
    {
        sensorManager.unregisterListener(this);
    }
}
