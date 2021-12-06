package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import prueba.proyectoappfitness.R;

public class MainActivity2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference referencia;

    private EditText nom, apep, apem, dire,email, edad, tele;
    RadioButton fem, mas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nom = findViewById(R.id.nom);
        apep = findViewById(R.id.apep);
        apem = findViewById(R.id.apem);
        dire = findViewById(R.id.dire);
        email = findViewById(R.id.email);
        tele = findViewById(R.id.tele);
        edad = findViewById(R.id.edad);

        fem = findViewById(R.id.fem);
        mas = findViewById(R.id.mas);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        referencia = database.getReference().child("usuario");


    }

    public void insertar(View v){
        try {
            Usuario2 usr = new Usuario2();
            String id_registro = UUID.randomUUID().toString();
            usr.setNombre(nom.getText().toString());
            usr.setApePaterno(apep.getText().toString());
            usr.setApeMaterno(apem.getText().toString());
            usr.setDireccion(dire.getText().toString());
            usr.setEdad(edad.getText().toString());
            usr.setEmail(email.getText().toString());
            usr.setId_usuario(id_registro);

            if(fem.isChecked())
                usr.setGenero("FEMENINO");
            if(mas.isChecked())
                usr.setGenero("MASCULINO");
            usr.setTelefono(tele.getText().toString());

            referencia.child(id_registro).setValue(usr);
            Toast.makeText(this, "Data insertada", Toast.LENGTH_SHORT).show();




        }catch (Exception e){
            Toast.makeText(this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void verLista(View v){
        Intent i = new Intent(this, ListaUsuarios2.class);
        startActivity(i);
    }
}