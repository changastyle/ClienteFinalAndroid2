package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolas.clientefinalandroid2.R;

import clienteNicoExpress.cliente.ManejadorCliente;
import serializable.Tarjeta;

public class Tab3 extends ActionBarActivity implements View.OnClickListener{
    private Button botonTab3LeerQR;
    private TextView tab4tvDinero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);

        botonTab3LeerQR = (Button) findViewById(R.id.botonTab3LeerQR);
        botonTab3LeerQR.setOnClickListener(this);

        tab4tvDinero = (TextView) findViewById(R.id.tab4tvDinero);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab3, menu);
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

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button)v ;
        if( botonPresionado.equals(botonTab3LeerQR))
        {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == 0)
        {

            if (resultCode == RESULT_OK)
            {
                int numeroEscaneado = Integer.parseInt(intent.getStringExtra("SCAN_RESULT").trim());

                Tarjeta tarjetaActual = ManejadorCliente.pedirDatosDeLaTarjeta(numeroEscaneado);

                this.tab4tvDinero.setText("$" + String.valueOf(ManejadorCliente.getTarjetaActual().getSaldo()));
                this.botonTab3LeerQR.setText("Nro Tarjeta: " + String.valueOf(ManejadorCliente.getTarjetaActual().getSerial()));
            }
            else if (resultCode == RESULT_CANCELED)
            {
                //TARJETA FALLO:
                /*tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");*/
            }
        }
    }
}
