package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import prueba.proyectoappfitness.R;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class P7 extends AppCompatActivity {
    RatingBar ratingBarra;
    Button InvarValorBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p7);

        final PantallaCarga pantallaCarga = new PantallaCarga(P7.this);

        ratingBarra = findViewById(R.id.rating_bar);
        InvarValorBtn = findViewById(R.id.valorarBtn);

       InvarValorBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String s = String.valueOf(ratingBarra.getRating());
               Toast.makeText(getApplicationContext(),  "Gracias por Valorarnos!"
               ,Toast.LENGTH_LONG).show();

               pantallaCarga.startLoadingDialog();
               Handler handler1 = new Handler();
               handler1.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       pantallaCarga.dismissDialog();
                   }
               },3000);
           }
       });
    }
}