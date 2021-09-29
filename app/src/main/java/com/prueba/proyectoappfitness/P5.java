package com.prueba.proyectoappfitness;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import prueba.proyectoappfitness.R;
import android.os.Bundle;

public class P5 extends AppCompatActivity {

    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.porridge,R.drawable.omelete,R.drawable.hotcake,R.drawable.panqueque};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        recyclerView = findViewById(R.id.recyclerView);



        s1  = getResources().getStringArray(R.array.comidas);
        s2  = getResources().getStringArray(R.array.descripcion);


        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}