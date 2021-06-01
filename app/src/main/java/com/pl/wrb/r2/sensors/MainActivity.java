package com.pl.wrb.r2.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorsManagement sensorsManagement;
    RefreshThread refreshThread;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);

        sensorsManagement = new SensorsManagement((SensorManager) getSystemService(Context.SENSOR_SERVICE));
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorsManagement.setSensorsListeners();
        refreshThread = new RefreshThread();
        refreshThread.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        sensorsManagement.unsetSensorsListeners();
        refreshThread.stopMe();
    }
    class RefreshThread extends Thread
    {
        boolean isRunning = true;

        void stopMe(){
            isRunning = false;
        }

        @Override
        public void run() {
            super.run();
            while (isRunning)
            {
                try {
                    sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView1.setText(sensorsManagement.getSensorsInfo());
                        }
                    });
                }catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
