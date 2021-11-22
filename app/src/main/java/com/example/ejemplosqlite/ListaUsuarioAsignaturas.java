package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adaptadores.AdaptadorAsignaturas;
import com.example.objetos.Asignaturas;
import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.Asignatura;
import com.example.sqlite.esquemas.UsrAsig;

import java.util.ArrayList;
import java.util.List;

import prueba.proyectoappfitness.R;

public class ListaUsuarioAsignaturas extends AppCompatActivity {

    private Spinner asignaturasAgregar;
    private RecyclerView listauserAsignaturas;
    private int id_usuario;
    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;
    ArrayList<Asignaturas> listaAsignaturas = new ArrayList<>();
    OperacionesCRUD instancia;
    List<String> dataSpinner = new ArrayList<String>();
    ArrayAdapter adaptadorSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario_asignaturas);


        if(null != this.getIntent()){
            if(null != this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_usuario = parametrosEntrada.getInt("id_usuario");

            }
        }
        asignaturasAgregar = findViewById(R.id.userasignaturas);
        listauserAsignaturas = findViewById(R.id.listauserasignaturas);
        instancia = new  OperacionesCRUD(this,
                "miBD",
                null,
                5);

        llenarSpinner();
        llenaRecyclerView();

    }

    public void llenarSpinner(){
        String columnasObtenerSpinner[] = {Asignatura.Esquema.ID,
                Asignatura.Esquema.CODIGO,
                Asignatura.Esquema.DESCRIPCION};


        String condicionSpinner = Asignatura.Esquema.ID
                + " not in (select id_asignatura from usuario_asignatura where id_usuario = ?)";

        String valoresCondicionSpinner[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturasNoAsociadas = instancia.obtenerDatos(columnasObtenerSpinner
             ,condicionSpinner, valoresCondicionSpinner, Asignatura.Esquema.TABLE_NAME);

        if(asignaturasNoAsociadas == null){
            Toast.makeText(this, "No se obtuvieron asignaturas para agregar desde BD", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0; i < asignaturasNoAsociadas.size();i++){
                ContentValues auxiliar = asignaturasNoAsociadas.get(i);
                String opcionSpinner = "";

                for(String key: auxiliar.keySet()){
                    opcionSpinner+=auxiliar.get(key).toString()+":";

                }//fin for interno
                dataSpinner.add(opcionSpinner);
            }//fin for
        }

        adaptadorSpinner = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,
                dataSpinner);
        asignaturasAgregar.setAdapter(adaptadorSpinner);



    }

    public void llenaRecyclerView() {
        //obtener todas las asignaturas asociadas al usuario
        String columnasObtener[] = {Asignatura.Esquema.ID,
        Asignatura.Esquema.CODIGO,
        Asignatura.Esquema.DESCRIPCION};

        String condicion = Asignatura.Esquema.ID
                + " in (select id_asignatura from usuario_asignatura where id_usuario = ?)";

        String valoresCondicion[] = {String.valueOf(id_usuario)};

        List<ContentValues> asignaturasUsuario = instancia.obtenerDatos(columnasObtener,
                condicion,
                valoresCondicion,
                Asignatura.Esquema.TABLE_NAME);

        if(asignaturasUsuario == null ){
            Toast.makeText(this, "No se obtuvieron asignaturas asociadas al usuario", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0; i < asignaturasUsuario.size();i++){
                ContentValues auxiliar = asignaturasUsuario.get(i);
                Asignaturas nuevaUsrAsig = new Asignaturas();
                for(String key: auxiliar.keySet()){
                    switch(key.toString()){
                        case Asignatura.Esquema.ID:
                            nuevaUsrAsig.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            nuevaUsrAsig.setCodigo(auxiliar.get(key).toString());
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            nuevaUsrAsig.setDescripcion(auxiliar.get(key).toString());
                            break;

                    }//fin switch
                }//fin for interno
                listaAsignaturas.add(nuevaUsrAsig);
            }//fin for
        }

        listauserAsignaturas.setHasFixedSize(true);
        manejador = new LinearLayoutManager(this);
        adaptador = new AdaptadorAsignaturas(listaAsignaturas);
        listauserAsignaturas.setLayoutManager(manejador);
        listauserAsignaturas.setAdapter(adaptador);


    }

    public void addAsignUsuario(View v){
        ContentValues nuevo_user_asignatura = new ContentValues();
        nuevo_user_asignatura.put("id_usuario",id_usuario);
        String itemseleccionadoSpinner = asignaturasAgregar.getSelectedItem().toString();

        int posicionItemSeleccionado = asignaturasAgregar.getSelectedItemPosition();

        String dataItem[] = itemseleccionadoSpinner.split(":");
        nuevo_user_asignatura.put("id_asignatura",dataItem[2]);

        long ret = instancia.insertTabla(nuevo_user_asignatura, UsrAsig.Esquema.TABLE_NAME);

        if(ret == 0){
            Toast.makeText(this, "No logro insertar usuario asignatura", Toast.LENGTH_SHORT).show();


        }else{
            Asignaturas nueva = new Asignaturas(dataItem[0],dataItem[1],Integer.parseInt(dataItem[2]));
            listaAsignaturas.add(nueva);
            adaptador = new AdaptadorAsignaturas(listaAsignaturas);

            listauserAsignaturas.setAdapter(adaptador);

            dataSpinner.remove(posicionItemSeleccionado);
            adaptadorSpinner = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dataSpinner);

            asignaturasAgregar.setAdapter(adaptadorSpinner);

        }
    }

}