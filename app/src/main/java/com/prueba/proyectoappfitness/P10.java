package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import prueba.proyectoappfitness.R;

public class P10 extends AppCompatActivity {


    private Button objBtnHilo2;
    private SensorManager manejadorSensores;
    private Sensor instanciaSensorAcelerometro;

    Button btn_start,btn_stop,btn_reset;
    Chronometer chronometro;
    Boolean correr=false;
    long detenerse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p10);

        btn_start=findViewById(R.id.btn_start);
        btn_stop=findViewById(R.id.btn_stop);
        btn_reset=findViewById(R.id.btn_reset);
        chronometro=findViewById(R.id.chronometro);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometro();

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopChronometro();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometro();
            }
        });


        objBtnHilo2 = findViewById(R.id.button2);

        objBtnHilo2.setOnClickListener(eventoBtn2);

        manejadorSensores = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        instanciaSensorAcelerometro = manejadorSensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(instanciaSensorAcelerometro == null){
            System.out.println("No existe sensor acelerometro en el dispositivo");
            finish();
        }


    }

    private void resetChronometro() {
        chronometro.setBase(SystemClock.elapsedRealtime());
        detenerse=0;
    }

    private void stopChronometro() {
        if (correr){
            chronometro.stop();
            detenerse = SystemClock.elapsedRealtime() - chronometro.getBase();
            correr=false;
        }
    }

    private void startChronometro() {
        if(!correr){
            chronometro.setBase(SystemClock.elapsedRealtime() - detenerse);
            chronometro.start();
            correr=true;
        }
    }

    private View.OnClickListener eventoBtn2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ReproductorMusica hiloSegundoPlano = new ReproductorMusica(P10.this,20);
            hiloSegundoPlano.start();
        }
    };

    private int contadorMovimientos = 0;
    private SensorEventListener eventoCapturaInformacion = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float posicionEnX = sensorEvent.values[0];
            System.out.println(" X ES : "+posicionEnX);
            System.out.println("Y ES : "+ sensorEvent.values[1]);
            System.out.println(" Z ES : "+ sensorEvent.values[2]);

            if(posicionEnX <-5 && contadorMovimientos == 0){
                contadorMovimientos++;
                System.out.println("Se movio dispositivo a la derecha");
            }else if(posicionEnX>5 && contadorMovimientos == 1){
                contadorMovimientos++;
                System.out.println("Se movio dispositivo a la izquierda");
            }

            if(contadorMovimientos == 2){
                Intent i = new Intent(P10.this,Emergencia.class);
                startActivity(i);

            }
            //System.out.println("Aceleracion en X : "+ sensorEvent.values[0]);
            //System.out.println("Aceleracion en Y : "+ sensorEvent.values[1]);
            //System.out.println("Aceleracion en Z : "+ sensorEvent.values[2]);

        }//fin onSensorChanged

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onPause() {
        manejadorSensores.unregisterListener(eventoCapturaInformacion);
        super.onPause();
    }

    @Override
    protected void onResume() {
        manejadorSensores.registerListener(eventoCapturaInformacion, instanciaSensorAcelerometro,
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
}