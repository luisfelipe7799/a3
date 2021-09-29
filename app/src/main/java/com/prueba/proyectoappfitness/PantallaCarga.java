package com.prueba.proyectoappfitness;

import android.app.Activity;
import android.app.AlertDialog;
import prueba.proyectoappfitness.R;
import android.view.LayoutInflater;

public class PantallaCarga {

    private Activity activity1;
    private AlertDialog dialog;

    PantallaCarga(Activity myActivity1){
        activity1= myActivity1;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(activity1);

        LayoutInflater inflater1 = activity1.getLayoutInflater();
        builder1.setView(inflater1.inflate(R.layout.custom_progress_bar,null));
        builder1.setCancelable(true);

        dialog = builder1.create();
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }
}
