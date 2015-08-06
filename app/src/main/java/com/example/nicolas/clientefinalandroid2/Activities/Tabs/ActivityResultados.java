package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones.ThreadProgress;
import com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones.ThreadWaiteador;
import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import clienteNicoExpress.cliente.ManejadorCliente;
import serializable.ConjuntoDevuelto;

public class ActivityResultados extends ActionBarActivity implements View.OnClickListener
{
    private TextView textViewResultadosDinero;
    private ArrayList<TextView> arrTextsViewsDeResultados;
    private TextView tvResultados1,tvResultados2,tvResultados3,tvResultados4,tvResultados5;
    private TextView tvResultados6,tvResultados7,tvResultados8,tvResultados9,tvResultados10;
    private Button botonResultadosIZQ, botonResultadosDER;
    private ProgressBar barraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_resultados);

        textViewResultadosDinero = (TextView) findViewById(R.id.textViewResultadosDinero);

        arrTextsViewsDeResultados = new ArrayList<TextView>();
        tvResultados1 = (TextView) findViewById(R.id.tvResultados1);arrTextsViewsDeResultados.add(tvResultados1);
        tvResultados2 = (TextView) findViewById(R.id.tvResultados2);arrTextsViewsDeResultados.add(tvResultados2);
        tvResultados3 = (TextView) findViewById(R.id.tvResultados3);arrTextsViewsDeResultados.add(tvResultados3);
        tvResultados4 = (TextView) findViewById(R.id.tvResultados4);arrTextsViewsDeResultados.add(tvResultados4);
        tvResultados5 = (TextView) findViewById(R.id.tvResultados5);arrTextsViewsDeResultados.add(tvResultados5);
        tvResultados6 = (TextView) findViewById(R.id.tvResultados6);arrTextsViewsDeResultados.add(tvResultados6);
        tvResultados7 = (TextView) findViewById(R.id.tvResultados7);arrTextsViewsDeResultados.add(tvResultados7);
        tvResultados8 = (TextView) findViewById(R.id.tvResultados8);arrTextsViewsDeResultados.add(tvResultados8);
        tvResultados9 = (TextView) findViewById(R.id.tvResultados9);arrTextsViewsDeResultados.add(tvResultados9);
        tvResultados10 = (TextView) findViewById(R.id.tvResultados10);arrTextsViewsDeResultados.add(tvResultados10);


        botonResultadosIZQ = (Button) findViewById(R.id.botonResultadosIZQ);botonResultadosIZQ.setOnClickListener(this);
        botonResultadosDER = (Button) findViewById(R.id.botonResultadosDER);botonResultadosDER.setOnClickListener(this);

        barraProgreso = (ProgressBar) findViewById(R.id.barraProgresoVentanaResultados);
        barraProgreso.setVisibility(View.INVISIBLE);

        //CARGO LOS DATOS EN LA VISTA:

        if(ManejadorCliente.getConjuntoDevuelto() != null)
        {
            ConjuntoDevuelto conjuntoDevuelto = ManejadorCliente.getConjuntoDevuelto();

            //Toast.makeText(this, "$" +  conjuntoDevuelto.getTarjetaDevuelta().getSaldo() ,Toast.LENGTH_SHORT).show();
            Intent intentToActivityTransparente = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones.Transparente.class);

            if(conjuntoDevuelto.dineroTotalGanado() == 0)
            {
                textViewResultadosDinero.setText("No Ganador");
                intentToActivityTransparente.putExtra("cual", "fracaso");
            }
            else
            {
                textViewResultadosDinero.setText("$" + String.valueOf(conjuntoDevuelto.dineroTotalGanado()));
                intentToActivityTransparente.putExtra("cual","exito");
            }

            startActivity(intentToActivityTransparente);


            int contador = 0;
            for(TextView textViewActual : arrTextsViewsDeResultados)
            {
                textViewActual.setText(conjuntoDevuelto.getArrNumerosSorteados().get(contador));
                contador++;
            }
        }
    }

    public void onClick(View v)
    {
        //Toast.makeText(this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
        Button botonPresionado = (Button) v;
        Intent intentQueVuelveATabActivity = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.VentanaContabulaciones.class);


        if(botonPresionado.equals(botonResultadosIZQ))
        {
            ManejadorCliente.vaciarConjuntoJugadas();

            intentQueVuelveATabActivity.putExtra("tab?",1);
            startActivity(intentQueVuelveATabActivity);

            this.finish();
        }
        else if(botonPresionado.equals(botonResultadosDER))
        {
            botonResultadosDER.setEnabled(false);
            botonResultadosIZQ.setEnabled(false);

            //ANIMACIONES DE ESPERA:
            //ThreadProgress threadProgress = new ThreadProgress(barraProgreso);
            //threadProgress.start();
            barraProgreso.setVisibility(View.VISIBLE);
            //Toast.makeText(this,"Enviando..",Toast.LENGTH_SHORT).show();

            if(ManejadorCliente.getConjuntoJugadasActuales().getArrJugadas().length > 0)
            {
                ManejadorCliente.enviarConjuntoJugadasAlServer();
            }


            intentQueVuelveATabActivity.putExtra("tab?", 2);
            startActivity(intentQueVuelveATabActivity);
            this.finish();
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
        return ManejadorCliente.menuOperaciones(item,this);
    }
}
