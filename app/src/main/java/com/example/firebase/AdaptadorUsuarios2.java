package com.example.firebase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import prueba.proyectoappfitness.R;

public class AdaptadorUsuarios2 extends RecyclerView.Adapter<AdaptadorUsuarios2.UsuarioHolder> {

    private ArrayList<Usuario2> usuario2Desplegar;
    FirebaseDatabase database;
    DatabaseReference referencia;



    public static class UsuarioHolder extends RecyclerView.ViewHolder{

        public ImageView avatar;
        public TextView nombre;
        public TextView rut;
        public ImageButton eliminar;
        public ImageButton detalle;
        public ImageButton editar;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nombre = itemView.findViewById(R.id.name);
            rut = itemView.findViewById(R.id.rut);
            eliminar = itemView.findViewById(R.id.eliminar);
            detalle = itemView.findViewById(R.id.detalle);
            editar = itemView.findViewById(R.id.editar);
        }


    }

    public AdaptadorUsuarios2(ArrayList<Usuario2> usuario2DesplegarIn) {
        usuario2Desplegar = usuario2DesplegarIn;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario2,parent,
                false);

        UsuarioHolder holder = new UsuarioHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        Usuario2 item = usuario2Desplegar.get(position);
        if(item.getGenero().toUpperCase().equals("MASCULINO"))
            holder.avatar.setImageResource(R.drawable.avatar_mas);
        else
            holder.avatar.setImageResource(R.drawable.avatar_fem);

        holder.nombre.setText(item.getNombre()+"");

        holder.rut.setText(item.getEmail());

        //boton eliminar

        //setear listener a cada boton eliminar de cada  item de la lista de usuarios
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseApp.initializeApp(view.getContext());
                database = FirebaseDatabase.getInstance();
                referencia = database.getReference().child("usuario");

                referencia.child(String.valueOf(item.getId_usuario())).removeValue();
                AdaptadorUsuarios2.this.usuario2Desplegar.remove(holder.getAdapterPosition());
                AdaptadorUsuarios2.this.notifyDataSetChanged();

            }
        });



        //boton editar

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent edicionUser = new Intent(view.getContext(),EditComida2.class);

                edicionUser.putExtra("id",item.getId_usuario());
                edicionUser.putExtra("nom",item.getNombre());
                edicionUser.putExtra("apePaterno",item.getApePaterno());
                edicionUser.putExtra("apeMaterno",item.getApeMaterno());
                edicionUser.putExtra("email",item.getEmail());
                edicionUser.putExtra("telefono",item.getTelefono());
                edicionUser.putExtra("direccion",item.getDireccion());
                edicionUser.putExtra("genero",item.getGenero());
                edicionUser.putExtra("edad",item.getEdad());

                view.getContext().startActivity(edicionUser);

            }
        });
    }

    @Override
    public int getItemCount() {
        return usuario2Desplegar.size();
    }

}
