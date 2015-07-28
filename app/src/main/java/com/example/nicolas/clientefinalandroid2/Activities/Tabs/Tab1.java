package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import clienteNicoExpress.cliente.ManejadorCliente;
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
        agregarListeners();


        //SETEO EL TEXTO DE LOS BOTONES:

        if(ManejadorCliente.getTarjetaActual() != null)
        {
            cargarTarjeta(ManejadorCliente.getTarjetaActual());
        }

        int contador = 0;
        for(Jugada jugada : ManejadorCliente.getConjuntoJugadasActuales().getArrJugadas())
        {
            if(! jugada.estoyVacia())
            {
                arrBotones.get(contador).setText(jugada.getNumero() + " x $" + jugada.getDineroApostado() + ".00");
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

        if(ManejadorCliente.getTarjetaActual() != null)
        {
            this.botonTarjeta.setText(String.valueOf(ManejadorCliente.getTarjetaActual() .getSerial()));
        }


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
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);
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
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == 0)
        {

            if (resultCode == RESULT_OK)
            {
                int numeroEscaneado = Integer.parseInt(intent.getStringExtra("SCAN_RESULT").trim());

                Tarjeta tarjetaActual = ManejadorCliente.pedirDatosDeLaTarjeta(numeroEscaneado);
                System.out.println("Tarjeta Actual:" + tarjetaActual);
                ManejadorCliente.setTarjetaActual(tarjetaActual);
                System.out.println("M TARJETA ACUTAL:" + ManejadorCliente.getTarjetaActual());

                this.cargarTarjeta(tarjetaActual);
            }
            else if (resultCode == RESULT_CANCELED)
            {
                //TARJETA FALLO:
                /*tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");*/
            }
        }
    }

    private void cargarTarjeta(Tarjeta tarjetaActual)
    {
        if(tarjetaActual != null && !tarjetaActual.estaVacia() )
        {
            this.botonTarjeta.setText(ManejadorCliente.getTarjetaActual().getSerial());
            this.botonTarjeta.setBackgroundResource(R.drawable.boton_verde);
        }
        else
        {
            this.botonTarjeta.setText(R.string.textoBotonTarjeta);
            this.botonTarjeta.setBackgroundResource(R.drawable.boton_tarjeta);
        }

    }
}
