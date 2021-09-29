package com.prueba.proyectoappfitness;

import android.content.Context;
import android.media.MediaPlayer;

import prueba.proyectoappfitness.R;

public class ReproductorMusica extends Thread{

    private int duracion_musica=0;
    Context contexto_llamada;
    private MediaPlayer reproductorMusica;

    public ReproductorMusica (Context contexto , int segundos_duracion){
        super();
        this.contexto_llamada=contexto;
        this.duracion_musica= segundos_duracion;
    }
    @Override
    public void run(){
        super.run();

        reproductorMusica = MediaPlayer.create(this.contexto_llamada, R.raw.musica_gym);
        reproductorMusica.start();

        try{
            Thread.sleep(this.duracion_musica*1000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        reproductorMusica.stop();
    }
}
