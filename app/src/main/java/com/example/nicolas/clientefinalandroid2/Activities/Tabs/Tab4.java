package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nicolas.clientefinalandroid2.R;

import clienteNicoExpress.cliente.ManejadorCliente;

public class Tab4 extends ActionBarActivity implements View.OnClickListener
{

    private Button botonWebsiteDesarrollador;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4);
        //botonWebsiteDesarrollador = (Button) findViewById(R.id.botonwebsitedeldesarrollador);
        //botonWebsiteDesarrollador.setOnClickListener(this);
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
        return ManejadorCliente.menuOperaciones(item, this);
    }
    public void goToDeveloperWebsite(View v)
    {
        String url = getResources().getString(R.string.websiteDeveloper);
        Intent intentToWebBrowser = new Intent(Intent.ACTION_VIEW);
        intentToWebBrowser.setData(Uri.parse(url));
        startActivity(intentToWebBrowser);
    }
    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        /*
        if (botonPresionado ==  botonWebsiteDesarrollador)
        {
            String url = "http://ngrossi.ddns.net";
            Intent intentToWebBrowser = new Intent(Intent.ACTION_VIEW);
            intentToWebBrowser.setData(Uri.parse(url));
            startActivity(intentToWebBrowser);
        }*/
    }
}
