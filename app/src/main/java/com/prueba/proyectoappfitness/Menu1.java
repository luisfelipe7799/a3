package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import prueba.proyectoappfitness.R;

public class Menu1 extends AppCompatActivity {

    ImageButton irAP2;
    ImageButton irAP8;
    ImageButton irAP5;
    ImageButton irAP9;
    ImageButton irAP10;
    ImageButton irAP11;
    ImageButton irAP12;
    Button irAP7;
    FloatingActionButton irAP4;

    //ProgressBar progressBarAnimation;
    //ObjectAnimator progressAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1_main);



        final PantallaCargaHorizontal pantallaCargaHorizontal = new PantallaCargaHorizontal(Menu1.this);

        irAP2 = (ImageButton) findViewById(R.id.imgP2Btn);
        irAP8 = (ImageButton) findViewById(R.id.imgP8Btn);
        irAP5 = (ImageButton) findViewById(R.id.imgP5Btn);
        irAP7 = (Button) findViewById(R.id.valoranosBtn);
        irAP4 = (FloatingActionButton) findViewById(R.id.notasFab);
        irAP10 = (ImageButton) findViewById(R.id.imgP10Btn);




        //para el progress Bar Horizontal
        pantallaCargaHorizontal.startLoadingDialog();
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {

                pantallaCargaHorizontal.dismissDialog1();
            }
        },2000);


        irAP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, P2.class);
                startActivity(intent);
            }
        });

        irAP8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, P8.class);
                startActivity(intent);
            }
        });

        irAP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, P5.class);
                startActivity(intent);
            }
        });

        irAP7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, P7.class);
                startActivity(intent);
            }
        });

        irAP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, P4.class);
                startActivity(intent);
            }
        });

        irAP10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, P10.class);
                startActivity(intent);
            }
        });
    }


}