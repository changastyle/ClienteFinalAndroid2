package com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.clientefinalandroid2.R;

import cliente.ManejadorCliente;

public class LoadingActivityResultados extends ActionBarActivity
{
    private TextView textViewNumeroLeidoLoadingActivityResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        textViewNumeroLeidoLoadingActivityResultados = (TextView) findViewById(R.id.textViewNumeroLeidoLoadingActivity);
        textViewNumeroLeidoLoadingActivityResultados.setText(R.string.msg_loading_activity_resultados);
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
        return Controller.ManejadorCliente.menuOperaciones(item,this);
    }
}
