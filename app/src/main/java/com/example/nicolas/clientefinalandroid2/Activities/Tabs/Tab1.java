package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nicolas.clientefinalandroid2.Activities.ActivityCargaJugada;
import com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones.LoadingActivityWaitingForTarjetaFromQR;
import com.example.nicolas.clientefinalandroid2.Activities.Tasks.TaskEnviarJugadas;
import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import Controller.ManejadorCliente;
import serializable.Jugada;
import serializable.Tarjeta;

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
        setearTextoABotones();
        agregarListeners();


    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        habilitarBotones();
    }

    //METODOS DE BOTONES:
    private void setearTextoABotones()
    {
        //SETEO EL TEXTO DE LOS BOTONES:
        if(ManejadorCliente.getTarjetaActual() != null)
        {
            cargarTarjeta(ManejadorCliente.getTarjetaActual());
        }

        habilitarBotones();
        int contador = 0;
        for(Jugada jugada : ManejadorCliente.getConjuntoJugadasActuales().getArrJugadas())
        {
            if(! jugada.estoyVacia())
            {
                arrBotones.get(contador).setText(jugada.getNumero() + " x $" + jugada.getDineroApostado() );
            }
            else
            {
                String stringParseoContador = "";

                switch(contador)
                {
                    case 0:
                        stringParseoContador = "Primera";
                        break;
                    case 1:
                        stringParseoContador = "Segunda";
                        break;
                    case 2:
                        stringParseoContador = "Tercera";
                        break;
                    case 3:
                        stringParseoContador = "Cuarta";
                        break;
                    case 4:
                        stringParseoContador = "Quinta";
                        break;
                    case 5:
                        stringParseoContador = "Sexta";
                        break;
                    default:
                        stringParseoContador = "xxx";
                        break;
                }
                arrBotones.get(contador).setText( stringParseoContador + " Jugada " + jugada.getNumero() );
            }
            contador++;
        }
    }
    private void habilitarBotones()
    {
        //HABILITAR O DESABILITAR BOTONES DE JUEGO:
        if(ManejadorCliente.getTarjetaActual() != null && ! ManejadorCliente.getTarjetaActual().estaVacia())
        {
            for (Button botonActual :arrBotones)
            {
                botonActual.setEnabled(true);
            }
            botonEnviarJugadas.setEnabled(true);

            this.cargarTarjeta(ManejadorCliente.getTarjetaActual());

            //Toast.makeText(this,"Saldo disponible: $" + ManejadorCliente.getTarjetaActual().getSaldo() ,Toast.LENGTH_SHORT).show();

        }
        else
        {
            for (Button botonActual :arrBotones)
            {
                botonActual.setEnabled(false);
            }
            botonEnviarJugadas.setEnabled(false);
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
    //FIN METODOS DE BOTONES.



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
                this.finish();
            }
            contador++;
        }

        if (botonPresionado.equals(botonEnviarJugadas))
        {
            TaskEnviarJugadas taskEnviarJugadas = new TaskEnviarJugadas(this);
            taskEnviarJugadas.execute();

            Intent intentActivityLoadingResults = new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones.LoadingActivityResultados.class);
            startActivity(intentActivityLoadingResults);
        }
        else if( botonPresionado.equals(botonTarjeta))
        {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);
        }
    }
    public void listo()
    {
        this.finish();
        Intent intentAResultado = new Intent(this,com.example.nicolas.clientefinalandroid2.Activities.VentanaContabulaciones.class);
        intentAResultado.putExtra("tab?",2);
        startActivity(intentAResultado);
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == 0)
        {

            if (resultCode == RESULT_OK)
            {
                int numeroEscaneado = Integer.parseInt(intent.getStringExtra("SCAN_RESULT"));

                Intent intentToLoadingActivity = new Intent(this,LoadingActivityWaitingForTarjetaFromQR.class);
                intentToLoadingActivity.putExtra("numeroEscaneadoConElQR", numeroEscaneado);
                startActivity(intentToLoadingActivity);
            }
            else if (resultCode == RESULT_CANCELED)
            {
            }
        }
    }

    private void cargarTarjeta(Tarjeta tarjetaActual)
    {
        if(tarjetaActual != null && !tarjetaActual.estaVacia() )
        {
            this.botonTarjeta.setText("Nro Tarjeta: " + ManejadorCliente.getTarjetaActual().getSerial());
            this.botonTarjeta.setBackgroundResource(R.drawable.boton_verde);
        }
        else
        {
            this.botonTarjeta.setText(R.string.textoBotonTarjeta);
            this.botonTarjeta.setBackgroundResource(R.drawable.boton_tarjeta);
        }

    }
}
