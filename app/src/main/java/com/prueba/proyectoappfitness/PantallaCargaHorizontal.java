package com.prueba.proyectoappfitness;

import android.app.Activity;
import android.app.AlertDialog;
import prueba.proyectoappfitness.R;
import android.view.LayoutInflater;

public class PantallaCargaHorizontal {

    //ProgressBar barraProgreso;
    //ObjectAnimator animacionBarra;

    private Activity activity2;
    private AlertDialog dialog1;



    //barraProgreso = findViewById(R.id.progressBarHorizontal);
    //animacionBarra = ObjectAnimator.ofInt(barraProgreso,"progress",100);
        //animacionBarra.setDuration(8000);

    PantallaCargaHorizontal(Activity myActivity2){
        activity2= myActivity2;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(activity2);

        LayoutInflater inflater2 = activity2.getLayoutInflater();
        builder2.setView(inflater2.inflate(R.layout.barra_horizontal,null));
        builder2.setCancelable(true);



        dialog1 = builder2.create();
        dialog1.show();
    }

    void dismissDialog1(){
        dialog1.dismiss();
    }

    //public  void iniciar(View v){
      //  animacionBarra.start();
    //}
}
