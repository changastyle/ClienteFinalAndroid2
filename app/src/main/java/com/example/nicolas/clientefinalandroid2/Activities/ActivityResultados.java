package com.example.nicolas.clientefinalandroid2.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import cliente.ManejadorCliente;
import serializable.ConjuntoDevuelto;
import serializable.ConjuntoJugadas;
import serializable.RespuestaJugada;

public class ActivityResultados extends ActionBarActivity implements View.OnClickListener
{
    private TextView textViewResultadosDinero;
    private ArrayList<TextView> arrTextsViewsDeResultados;
    private TextView tvResultados1,tvResultados2,tvResultados3,tvResultados4,tvResultados5;
    private TextView tvResultados6,tvResultados7,tvResultados8,tvResultados9,tvResultados10;
    private Button botonResultadosIZQ, botonResultadosDER;

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

        //CARGO LOS DATOS EN LA VISTA:

        ConjuntoDevuelto conjuntoDevuelto = ManejadorCliente.getConjuntoDevuelto();

        System.out.println("NUMEROS SORTEADOS:" + conjuntoDevuelto.toString());
        textViewResultadosDinero.setText("$" + String.valueOf(conjuntoDevuelto.dineroTotalGanado()) + ",00");

        int contador = 0;
        for(TextView textViewActual : arrTextsViewsDeResultados)
        {
            textViewActual.setText("Numero: " + conjuntoDevuelto.getArrNumerosSorteados().get(contador));
            contador++;
        }
    }

    public void onClick(View v)
    {
        //Toast.makeText(this,String.valueOf(v.getId()),Toast.LENGTH_SHORT).show();
        Button botonPresionado = (Button) v;
        Intent intentQueVuelveATabActivity = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.VentanaContabulaciones.class);
        intentQueVuelveATabActivity.putExtra("tab?",1);
        Intent intentAutoCall = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.ActivityResultados.class);

        if(botonPresionado.equals(botonResultadosIZQ))
        {
            ManejadorCliente.vaciarConjuntoJugadas();
            startActivity(intentQueVuelveATabActivity);
        }
        else if(botonPresionado.equals(botonResultadosDER))
        {
            //Toast.makeText(this,"Volviste a jugar los mismos numeros",Toast.LENGTH_SHORT).show();
            ManejadorCliente.enviarConjuntoJugadasAlServer();
            startActivity(intentAutoCall);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_resultados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
