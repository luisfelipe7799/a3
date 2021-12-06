package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import prueba.proyectoappfitness.R;

public class EditComida2 extends AppCompatActivity {

    private EditText nom,apep,apem,email,edad,tele,dire;
    private RadioButton mas,fem;

    private String id_user_entrada="";
    private String edad_user_entrada="";
    private String nom_user_entrada="";
    private String apep_user_entrada="";
    private String apem_user_entrada="";
    private String email_user_entrada="";
    private String telefono_user_entrada="";
    private String dire_user_entrada="";
    private String genero_user_entrada="";

    FirebaseDatabase database;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comida2);

        if(null != this.getIntent()){
            if(null !=this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_user_entrada = parametrosEntrada.getString("id");
                edad_user_entrada = parametrosEntrada.getString("edad");
                telefono_user_entrada = parametrosEntrada.getString("telefono");
                nom_user_entrada = parametrosEntrada.getString("nom");
                apep_user_entrada = parametrosEntrada.getString("apePaterno");
                apem_user_entrada = parametrosEntrada.getString("apeMaterno");
                email_user_entrada = parametrosEntrada.getString("email");
                dire_user_entrada = parametrosEntrada.getString("direccion");
                genero_user_entrada = parametrosEntrada.getString("genero");

            }
        }

        nom = findViewById(R.id.nomEdit2);
        nom.setText(nom_user_entrada);
        apep = findViewById(R.id.apepEdit2);
        apep.setText(apep_user_entrada);
        apem = findViewById(R.id.apemEdit2);
        apem.setText(apem_user_entrada);
        email = findViewById(R.id.emailEdit2);
        email.setText(email_user_entrada);
        edad = findViewById(R.id.edadEdit2);
        edad.setText(edad_user_entrada);
        tele = findViewById(R.id.teleEdit2);
        tele.setText(telefono_user_entrada);
        dire = findViewById(R.id.direEdit2);
        dire.setText(dire_user_entrada);
        mas = findViewById(R.id.masEdit2);
        fem = findViewById(R.id.femEdit2);

        if(genero_user_entrada.toUpperCase().equals("MASCULINO")){
            mas.setChecked(true);
            fem.setChecked(false);
        }else{
            mas.setChecked(false);
            fem.setChecked(true);
        }

    }

    public void editarUsuario(View v){
        try {
            FirebaseApp.initializeApp(this);
            database = FirebaseDatabase.getInstance();
            referencia = database.getReference().child("usuario");
            Usuario2 usr = new Usuario2();

            usr.setId_usuario(id_user_entrada);
            usr.setNombre(nom.getText().toString());
            usr.setApePaterno(apep.getText().toString());
            usr.setApeMaterno(apem.getText().toString());
            usr.setDireccion(dire.getText().toString());
            usr.setEdad(edad.getText().toString());
            usr.setEmail(email.getText().toString());
            if(fem.isChecked())
                usr.setGenero("FEMENINO");
            if(mas.isChecked())
                usr.setGenero("MASCULINO");
            usr.setTelefono(tele.getText().toString());
            referencia.child(id_user_entrada).setValue(usr);
            Toast.makeText(this, "Comida actualizada", Toast.LENGTH_SHORT).show();







        }catch(Exception e){
            Toast.makeText(this, "Error actualizando data: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }


}