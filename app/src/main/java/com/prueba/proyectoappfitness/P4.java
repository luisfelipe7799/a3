package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import prueba.proyectoappfitness.R;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class P4 extends AppCompatActivity {
    //CheckedTextView ascheckedTextView;
    CheckBox asCheckbox;

    int contador = 0;
    ChipGroup contenedorChip;
    EditText textoparaChip;

    CheckedTextView box1;
    //CheckBox box2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);

        contenedorChip = findViewById(R.id.grupoChip);
        textoparaChip = findViewById(R.id.ingresarComida);


       box1= (CheckedTextView) findViewById(R.id.checkedTextView2);
       //box2 =(CheckBox) findViewById(R.id.checkBox);


       box1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(box1.isChecked()){
                   box1.setChecked(false);
                   Toast.makeText(getBaseContext(),"Falta la primera comida",Toast.LENGTH_SHORT).show();
               }
               else
                   box1.setChecked(true);
                    Toast.makeText(getBaseContext(),"Primera comida lista",Toast.LENGTH_SHORT).show();
           }
       });


        asCheckbox = (CheckBox)  findViewById(R.id.checkBox);
        //Para el checkbox y que muestre una alerta
        asCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"Ultima comida lista",Toast.LENGTH_SHORT).show();

                } else{
                    Toast.makeText(getBaseContext(),"Falta la ultima comida",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void agregarChipaContenedor (View v){
        String textoChip = "";
        textoChip = textoparaChip.getText().toString();
        contador = contador +1;

        Chip nuevoChip = new Chip(this);
        nuevoChip.setId(contador);
        nuevoChip.setText(textoChip);
        nuevoChip.setCheckable(true);
        nuevoChip.setCloseIcon(getDrawable(R.drawable.equis));
        nuevoChip.setCloseIconVisible(true);
        nuevoChip.setChipBackgroundColor(ColorStateList.valueOf(
                ContextCompat.getColor(this,R.color.fondoChipP)
        ));
        nuevoChip.setMinWidth(100);
        nuevoChip.setHeight(80);


        nuevoChip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenedorChip.removeView(view);
            }
        });

        contenedorChip.addView(nuevoChip);

    }












}