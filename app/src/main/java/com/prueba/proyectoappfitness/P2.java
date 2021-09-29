package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import prueba.proyectoappfitness.R;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class P2 extends AppCompatActivity {
    Switch aSwitch;
    EditText peso;
    EditText estatura;
    TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2);

        //final PantallaCarga pantallaCarga = new PantallaCarga(P2.this);

        aSwitch = (Switch)  findViewById(R.id.switch1);
        //Para el switch y que muestre una alerta
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"Si hizo ejercicio hoy",Toast.LENGTH_LONG).show();

                } else{
                    Toast.makeText(getBaseContext(),"No ha hecho ejercicio hoy",Toast.LENGTH_LONG).show();
                }

            }
        });


        //para tomar los numeros y hacer la operacion del IMC
        peso = (EditText) findViewById(R.id.PesoTxt);
        estatura =(EditText) findViewById(R.id.EstaturaTxt);
        resultado =(TextView) findViewById(R.id.resultadoTxv);


    }

    //toma los valores de peso y estatura, hace la operacion y muestra el resultado en la caja resultado
    public void Operacion(View view){


        double valor1=Double.parseDouble(peso.getText().toString());
        double valor2=Double.parseDouble(estatura.getText().toString());

        double res= (valor1 / (valor2 * valor2));

        //mostrar solo 2 decimales
        resultado.setText(String.format("%.2f",res));

        //pantallaCarga.startLoadingDialog();



    }
    //limpiar valores puestos
    public void limpiar(View view){

        resultado.setText(String.format(""));
        peso.setText(String.format(""));
        estatura.setText(String.format(""));





    }
}