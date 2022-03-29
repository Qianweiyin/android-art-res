package com.google.zxing;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.google.zxing.camera.CameraManager;
import com.google.zxing.camera.FrontLightMode;

public class AmbientLightManager implements SensorEventListener {
    private CameraManager cameraManager;
    private Sensor sensor;
    private final Context context;

    public AmbientLightManager(Context context) {
        this.context = context;
    }

    public void registerLightSensor(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        if (FrontLightMode.defaultMode() == FrontLightMode.AUTO) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE);
            this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            Sensor sensor = this.sensor;
            if (sensor != null) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager == null) {
            return;
        }
        if (f <= 45.0f) {
            cameraManager.setTorch(true);
        } else if (f >= 450.0f) {
            cameraManager.setTorch(false);
        }
    }

    public void stop() {
        if (this.sensor != null) {
            ((SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE)).unregisterListener(this);
            this.cameraManager = null;
            this.sensor = null;
        }
    }
}

