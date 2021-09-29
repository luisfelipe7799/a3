package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import prueba.proyectoappfitness.R;

public class Login extends AppCompatActivity {


    Button irAlMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);




        irAlMenu = (Button) findViewById(R.id.ingresarBtn);

        irAlMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Menu1.class);
                startActivity(intent);


            }
        });
    }



}