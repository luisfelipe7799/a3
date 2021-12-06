package com.example.firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import prueba.proyectoappfitness.R;

public class ListaUsuarios2 extends AppCompatActivity {

    private RecyclerView recycler;
    AdaptadorUsuarios2 adaptador;
    private ArrayList<Usuario2>data;


    FirebaseDatabase miBd;
    DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios2);


        FirebaseApp.initializeApp(this);
        miBd = FirebaseDatabase.getInstance();
        referencia = miBd.getReference().child("usuario");

        recycler = findViewById(R.id.lista);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<>();
        adaptador = new AdaptadorUsuarios2(data);
        recycler.setAdapter(adaptador);

        referencia.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();

                if(snapshot.exists()){
                    for(DataSnapshot auxiliar: snapshot.getChildren()){
                        Usuario2 objeto = auxiliar.getValue(Usuario2.class);
                        data.add(objeto);

                    }//fin for

                    adaptador.notifyDataSetChanged();
                }//fin id

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}