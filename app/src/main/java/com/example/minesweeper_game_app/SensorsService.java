package com.example.minesweeper_game_app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

interface SensorServiceListener {

    enum ALARM_STATE {
        ON,OFF
    }

    void alarmStateChanged(ALARM_STATE state);
}


public class SensorsService extends Service implements SensorEventListener {

    private final static String TAG = "SENSOR_SERVICE";

    private final double THRESHOLD = 1;

    private SensorServiceListener.ALARM_STATE currentAlarmState = SensorServiceListener.ALARM_STATE.OFF;

    // Binder given to clients
    private final IBinder mBinder = new SensorServiceBinder();

    SensorServiceListener mListener;

    SensorManager mSensorManager;
    Sensor mAccel;

    float firstEventAxisX=0.0f ;
    float firstEventAxisY =0.0f;
    float firstEventAxisZ =0.0f;

    public class SensorServiceBinder extends Binder {

        void onSeansorchanged(SensorEvent event){
            SensorsService.this.onSensorChanged(event);
            if(SensorsService.this.currentAlarmState==SensorServiceListener.ALARM_STATE.ON){
                Log.e("Binder", "ALARM!!!");

            }

        }


        void registerListener(SensorServiceListener listener) {
            Log.e("Binder", "registering...");
            mListener = listener;
        }

        void startSensors() {
            mSensorManager.registerListener(SensorsService.this,mAccel,SensorManager.SENSOR_DELAY_NORMAL);
        }

        void stopSensors() {
            mSensorManager.unregisterListener(SensorsService.this);
        }


    }




    public SensorsService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(mAccel != null) {
            Log.e("Sensors ouput" , "Accelerometer avilable");
        } else {
            Log.e("Sensors ouput" , "Accelerometer NOT Availible");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // Sensor event listener callbacks


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(firstEventAxisX == 0.0f) {

            firstEventAxisX= event.values[0];
            firstEventAxisY= event.values[1];
            firstEventAxisZ = event.values[2];
            Log.e(TAG,"FIRST EVENT!!!");

        } else {

            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            Log.e("First EVENR", "" + firstEventAxisX + " " + firstEventAxisY + " " + firstEventAxisZ);
            Log.e("Current EVENR", "" + event.values[0] + " " + event.values[1] + " " + event.values[2]);
//TODO check why mFirstEvent is equal to event
            double absDiffX = Math.abs(firstEventAxisX - event.values[0]);
            double absDiffY = Math.abs(firstEventAxisY - event.values[1]);
            double absDiffZ = Math.abs(firstEventAxisZ - event.values[2]);

            Log.e("DIFFS", "" + absDiffX + " " + absDiffY + " " + absDiffZ);

            SensorServiceListener.ALARM_STATE previousState = currentAlarmState;
            if (absDiffX > THRESHOLD || absDiffY > THRESHOLD || absDiffZ > THRESHOLD ) {
                this.currentAlarmState = SensorServiceListener.ALARM_STATE.ON;
               Log.e(TAG,"ALARM ON!");

            } else {
                this.currentAlarmState = SensorServiceListener.ALARM_STATE.OFF;
                Log.e(TAG,"ALARM OFF!");
            }

            if(previousState != currentAlarmState) {
                mListener.alarmStateChanged(currentAlarmState);
            }

        }

       // Log.e(TAG,event.values[0] + " " + event.values[1] + " " + event.values[2]);


    }
}


