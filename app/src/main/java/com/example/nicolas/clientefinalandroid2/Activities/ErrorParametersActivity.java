package com.example.nicolas.clientefinalandroid2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nicolas.clientefinalandroid2.R;

import Controller.ManejadorCliente;

public class ErrorParametersActivity extends ActionBarActivity implements View.OnClickListener
{

    private Button botonReintentarConexion, botonHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_parameters);

        botonReintentarConexion = (Button) findViewById(R.id.botonReintentarConexion);botonReintentarConexion.setOnClickListener(this);
        botonHelp = (Button) findViewById(R.id.botonHelp); botonHelp.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return ManejadorCliente.menuOperaciones(item,this);
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;


        if(botonPresionado.equals(botonReintentarConexion))
        {
            this.finish();
            Intent intentQueMeLlevaAlActivityInicial = new Intent(this,com.example.nicolas.clientefinalandroid2.Activities.ActivityCargaBarra.class);
            startActivity(intentQueMeLlevaAlActivityInicial);
        }
        else if(botonPresionado.equals(botonHelp))
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.telfonoDesarrollador)));
            startActivity(intent);
        }

    }
}
