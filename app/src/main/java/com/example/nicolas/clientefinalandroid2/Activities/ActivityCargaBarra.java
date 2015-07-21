package com.example.nicolas.clientefinalandroid2.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import Threads.ThreadBarraStatusWelcomeActivity;
import Threads.ThreadPedidorParametros;
import cliente.*;
import serializable.ParametrosEncapsuladosParaClientes;

import com.example.nicolas.clientefinalandroid2.R;


public class ActivityCargaBarra extends ActionBarActivity
{
    private int porcentajeBarra;
    private ProgressBar progressBar;
    private ImageView imagenTrebol;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_barra);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        porcentajeBarra = 0 ;
        imagenTrebol = (ImageView) findViewById(R.id.imagenTrebol);

        try
        {
            //THREAD 1: PIDE PARAMETROS PEPC al Servidor:
            //manejadorCliente = new ManejadorCliente();
            ThreadPedidorParametros threadPedidorParametros = new ThreadPedidorParametros();
            threadPedidorParametros.start();

            //THREAD 2: Actualiza la barra:
            ThreadBarraStatusWelcomeActivity threadBarraStatusWelcomeActivity = new ThreadBarraStatusWelcomeActivity(this);
            threadBarraStatusWelcomeActivity.start();


            RotateAnimation rotate = new RotateAnimation(0 ,360);
            rotate.setDuration(3000);
            imagenTrebol.startAnimation(rotate);

            //THREAD 1 FINISH: Espera a que terminen de pasar los parametros PEPC:
            threadPedidorParametros.join();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        MediaPlayer mp = MediaPlayer.create(this, R.raw.coin);
        mp.start();
    }
    public void pasarActivityPrincipal()
    {
        //SI EL CONTROLLER TIENE LOS PARAMETROS: ENTONCES PASO A LA TABED ACTIVITY SINO A LA ERROR ACTIVITY:

        if(ManejadorCliente.getPepc() != null)
        {
            Intent i = new Intent(this, VentanaContabulaciones.class);
            i.putExtra("tab?",1);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.ErrorParametersActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public ProgressBar getBarra()
    {
        ProgressBar barra = null;

        barra = this.progressBar;

        return barra;
    }
    public ImageView getTrebol()
    {
        return this.imagenTrebol;
    }
    public void rotarTrebol(int grados)
    {
        imagenTrebol.animate().rotation(1000).start();
    }
}
