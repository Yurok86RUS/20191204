package com.yur0k.lessontwohamework;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textTemperature;
    private TextView textHumidity;
    private SensorManager sensorManager;
    private Sensor sensorTemperature;
    private Sensor sensorHumidity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTemperature = findViewById(R.id.text_temperature);
        textHumidity = findViewById(R.id.text_humidity);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        sensorManager.registerListener(listenerTemperature, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listenerHumidity, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerTemperature, sensorTemperature);
        sensorManager.unregisterListener(listenerHumidity, sensorHumidity);
    }


    SensorEventListener listenerTemperature = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            showTemperatureSensor(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void showTemperatureSensor(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Текущая температура = ").append(event.values[0]).append(" С");
        textTemperature.setText(stringBuilder);
    }

    SensorEventListener listenerHumidity = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            showHumiditySensor(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void showHumiditySensor(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Текущая влвжность = ").append(event.values[0]).append(" мм");
        textHumidity.setText(stringBuilder);
    }

}
