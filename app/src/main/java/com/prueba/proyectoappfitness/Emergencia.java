package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import prueba.proyectoappfitness.R;

public class Emergencia extends AppCompatActivity {

    private Button botonEmergencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);

        botonEmergencia = findViewById(R.id.button);

        botonEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + "131" ));
                if (intent.resolveActivity(getPackageManager()) !=null){
                    startActivity(intent);
                }
            }
        });

    }
}