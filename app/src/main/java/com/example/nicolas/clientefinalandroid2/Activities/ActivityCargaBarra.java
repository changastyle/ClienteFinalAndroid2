package com.example.nicolas.clientefinalandroid2.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import Controller.ManejadorCliente;

import com.example.nicolas.clientefinalandroid2.Activities.Tasks.TaskPedirParametros;
import com.example.nicolas.clientefinalandroid2.R;


public class ActivityCargaBarra extends ActionBarActivity
{
    private int porcentajeBarra;
    private ProgressBar progressBarActivityCargaBarra;
    private ImageView imagenTrebol;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_barra);

        //barra = (ProgressBar) findViewById(R.id.progressBar);
        progressBarActivityCargaBarra = (ProgressBar) findViewById(R.id.progressBarActivityCargaBarra);
        progressBarActivityCargaBarra.setVisibility(View.INVISIBLE);
        porcentajeBarra = 0 ;
        imagenTrebol = (ImageView) findViewById(R.id.imagenTrebol);

        queHacerMientrasNoEstaListo();

        TaskPedirParametros taskPedirParametros = new TaskPedirParametros(this);
        taskPedirParametros.execute();

    }
    public void queHacerMientrasNoEstaListo()
    {
        RotateAnimation rotate = new RotateAnimation(0 ,360);
        rotate.setDuration(3000);
        imagenTrebol.startAnimation(rotate);

        if( cliente.ManejadorCliente.getTarjetaActual() == null)
        {
            progressBarActivityCargaBarra.setVisibility(View.VISIBLE);
        }
    }
    public void listo()
    {
        //Reproduce el sonido de las monedas:
        MediaPlayer mp = MediaPlayer.create(this, R.raw.coin);
        mp.start();
        pasarActivityPrincipal();
    }
    public void noListo()
    {
        pasarActivityPrincipal();
    }
    public void pasarActivityPrincipal()
    {
        //Si tengo los parametros en el controller: entonces voy a la activity con tabulaciones, sino voy a activity de error:

        if(ManejadorCliente.getPepc() != null)
        {
            Intent i = new Intent(this, VentanaContabulaciones.class);
            i.putExtra("tab?",1);
            startActivity(i);
            this.finish();
        }
        else
        {
            Intent i = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.ErrorParametersActivity.class);
            startActivity(i);
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return ManejadorCliente.menuOperaciones(item,this);
    }
    public ImageView getTrebol()
    {
        return this.imagenTrebol;
    }
}

