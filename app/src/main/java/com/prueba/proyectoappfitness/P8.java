package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import prueba.proyectoappfitness.R;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class P8 extends AppCompatActivity {

    AutoCompleteTextView auto;
    EditText autoComplete;
    Button toggle;
    EditText marca;
    Button boton1;
    RadioButton boton2;
    Button guardar;
    EditText resultado;
    Spinner comboEjercicios;

    MultiAutoCompleteTextView multi;
    EditText multiAutoCompleteEtx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p8);

        auto = (AutoCompleteTextView) findViewById(R.id.auto);
        multi =(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);

        String[] autos = getResources().getStringArray(R.array.autos);
        String[] comidasDelDia = getResources().getStringArray(R.array.comidasDelDia);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,autos);
        auto.setAdapter(adapter);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comidasDelDia);
        multi.setAdapter(adapter5);
        multi.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        autoComplete = findViewById(R.id.auto);
        toggle = findViewById(R.id.toggleButton);
        marca = findViewById(R.id.textMarca1);
        boton1 = findViewById(R.id.radioButtonBencina);
        boton2 = findViewById(R.id.radioButtonDiesel);
        guardar = findViewById(R.id.saveButton);
        resultado = findViewById(R.id.comentario);

        comboEjercicios = findViewById(R.id.spinner2);

        multiAutoCompleteEtx = findViewById(R.id.multiAutoCompleteTextView);



        ArrayAdapter<CharSequence> adapter23=ArrayAdapter.createFromResource(this,
                R.array.ejercicios, android.R.layout.simple_spinner_item);

        comboEjercicios.setAdapter(adapter23);


    }

    public void guardar(View v){

        String autoCompleteValor = "";
        String marcaValor= "";
        String toggleValor= "";
        String button1Valor= "";
        String button2Valor= "";
        String comboEjerciciosValor="";
        String multiAutoCompleteValor="";



        autoCompleteValor = autoComplete.getText().toString();
        marcaValor = marca.getText().toString();
        toggleValor = toggle.getText().toString();
        button1Valor = boton1.getText().toString();
        button2Valor = boton2.getText().toString();
        comboEjerciciosValor = comboEjercicios.getSelectedItem().toString();
        multiAutoCompleteValor = multiAutoCompleteEtx.getText().toString();

        resultado.setText("");
        resultado.append(autoCompleteValor+"\n");
        resultado.append(marcaValor +"\n");
        resultado.append(toggleValor +"\n");
        resultado.append(comboEjerciciosValor +"\n");
        resultado.append(multiAutoCompleteValor +"\n");


        if(boton2.isChecked()){
            resultado.append(button2Valor +"\n");
        }else{
            resultado.append(button1Valor +"\n");
        }





    }
}