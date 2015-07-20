package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import cliente.ManejadorCliente;
import serializable.Jugada;

public class Tab1 extends ActionBarActivity implements View.OnClickListener
{
    private Button Tab4But1,Tab4But2,Tab4But3,Tab4But4,Tab4But5,botonEnviarJugadas, botonTarjeta;
    private ArrayList<Button> arrBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);

        //CARGA DE BOTONES:
        arrBotones = new ArrayList<Button>();

        Tab4But1 = (Button) findViewById(R.id.Tab4But1);arrBotones.add(Tab4But1);
        Tab4But2 = (Button) findViewById(R.id.Tab4But2);arrBotones.add(Tab4But2);
        Tab4But3 = (Button) findViewById(R.id.Tab4But3);arrBotones.add(Tab4But3);
        Tab4But4 = (Button) findViewById(R.id.Tab4But4);arrBotones.add(Tab4But4);
        Tab4But5 = (Button) findViewById(R.id.Tab4But5);arrBotones.add(Tab4But5);
        botonEnviarJugadas = (Button)findViewById(R.id.botonEnviarJugadas);
        botonTarjeta = (Button)findViewById(R.id.botonTarjeta);
        agregarListeners();



        System.out.println("LENGTH" + ManejadorCliente.getConjuntoJugadasActuales().getArrJugadas().length )  ;

        int contador = 0;
        for(Jugada jugada : ManejadorCliente.getConjuntoJugadasActuales().getArrJugadas())
        {
            if(jugada != null)
            {
                System.out.println("J" + contador + ": " + jugada.toString());
                arrBotones.get(contador).setText(jugada.getNumero() + " x $" + jugada.getDineroApostado() + ".00");
            }
            contador++;
        }


    }
    private void agregarListeners()
    {
        for(Button b : arrBotones)
        {
            b.setOnClickListener(this);
        }
        botonEnviarJugadas.setOnClickListener(this);
        botonTarjeta.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;
        int contador = 0;
        for(Button botonEditarJugada : arrBotones)
        {
            if(botonPresionado.equals(botonEditarJugada))
            {
                Intent i = new Intent(this,ActivityCargaJugada.class);
                i.putExtra("jugada" , contador);
                startActivity(i);
            }
            contador++;
        }

        if (botonPresionado.equals(botonEnviarJugadas))
        {
            System.out.println("ESTOY A PUNTO DE MANDAR ESTE CONJUNTO AL SERVER:" + ManejadorCliente.getConjuntoJugadasActuales().toString());
            ManejadorCliente.setConjuntoDevuelto(ManejadorCliente.enviarConjuntoJugadasAlServer());

            System.out.println("RECIBI COMO CONJUNTO DEVUELTO:" + ManejadorCliente.getConjuntoDevuelto().toString());
            Intent intentAResultado = new Intent(this,com.example.nicolas.clientefinalandroid2.Activities.ActivityResultados.class);
            //intentAResultado.putExtra();
            startActivity(intentAResultado);
        }
        else if( botonPresionado.equals(botonTarjeta))
        {
            Intent intentQueMeLlevaALaCargaDeTarjeta = new Intent(this,com.example.nicolas.clientefinalandroid2.Activities.VentanaContabulaciones.class);
            intentQueMeLlevaALaCargaDeTarjeta.putExtra("tab?", 3);
            startActivity(intentQueMeLlevaALaCargaDeTarjeta);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab4, menu);
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
}
