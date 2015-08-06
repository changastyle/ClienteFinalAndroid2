package com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener
{
    @Override
    public void onClick(View v) {

    }
/*
    private Button botonQR;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonQR = (Button) findViewById(R.id.botonQR);botonQR.setOnClickListener(this);
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
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void abrirActivityTransparente(View v)
    {
        Button b = (Button) v;

        Intent intentToActivityTransparente = new Intent(this, Transparente.class);
        if(b.equals(findViewById(R.id.botonOK)))
        {
            intentToActivityTransparente.putExtra("cual","exito");
        }
        else
        {
            intentToActivityTransparente.putExtra("cual","fracaso");
        }

        startActivity(intentToActivityTransparente);
    }
    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button)v;

        if(botonPresionado.equals(botonQR))
        {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);	//Barcode Scanner to scan for us
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
               /* tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
            }
            else if (resultCode == RESULT_CANCELED)
            {
                tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");
            }
        }
    }*/
}
