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

import clienteNicoExpress.Threads.ThreadBarraStatusWelcomeActivity;
import clienteNicoExpress.Threads.ThreadPedidorParametros;
import clienteNicoExpress.cliente.*;

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

        //Reproduce el sonido de las monedas:
        MediaPlayer mp = MediaPlayer.create(this, R.raw.coin);
        mp.start();
    }
    public void pasarActivityPrincipal()
    {
        //Si tengo los parametros en el controller: entonces voy a la activity con tabulaciones, sino voy a activity de error:

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
        int id = item.getItemId();
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
