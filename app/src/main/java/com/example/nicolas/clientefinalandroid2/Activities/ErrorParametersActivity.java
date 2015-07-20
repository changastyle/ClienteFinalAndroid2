package com.example.nicolas.clientefinalandroid2.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nicolas.clientefinalandroid2.R;

public class ErrorParametersActivity extends ActionBarActivity implements View.OnClickListener
{

    private Button botonReintentarConexion;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_parameters);
        botonReintentarConexion = (Button) findViewById(R.id.botonReintentarConexion);botonReintentarConexion.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_error_parameters, menu);
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
        Button botonPresionado = (Button) v;


        if(botonPresionado.equals(botonReintentarConexion))
        {
            Intent intentQueMeLlevaAlActivityInicial = new Intent(this,com.example.nicolas.clientefinalandroid2.Activities.ActivityCargaBarra.class);
            startActivity(intentQueMeLlevaAlActivityInicial);
        }

    }
}
