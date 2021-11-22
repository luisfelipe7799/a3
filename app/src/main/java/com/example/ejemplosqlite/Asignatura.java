package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.OperacionesCRUD;

import prueba.proyectoappfitness.R;

public class Asignatura extends AppCompatActivity {


    EditText codigo;
    EditText descripcion;

    Button voler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura);

        codigo = (EditText) findViewById(R.id.codigo);
        descripcion = (EditText) findViewById(R.id.descripcion);
        voler = findViewById(R.id.volverAsignatura);

        voler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Asignatura.this, ListaAsignaturas.class);
                startActivity(intent);
            }
        });

    }

    public void insert (View view){
        OperacionesCRUD oper = new OperacionesCRUD(this,
                "miBD",
                null,
                5);

        ContentValues datosAsignatura = new ContentValues();
        datosAsignatura.put(com.example.sqlite.esquemas.Asignatura.Esquema.CODIGO, codigo.getText().toString());
        datosAsignatura.put(com.example.sqlite.esquemas.Asignatura.Esquema.DESCRIPCION, descripcion.getText().toString());

        long id_asignatura_insertado = 0;
        id_asignatura_insertado = oper.insertTabla(datosAsignatura, com.example.sqlite.esquemas.Asignatura.Esquema.TABLE_NAME);
        Toast.makeText(this,"id de asignatura insertada: "+ id_asignatura_insertado,Toast.LENGTH_LONG).show();
    }

}