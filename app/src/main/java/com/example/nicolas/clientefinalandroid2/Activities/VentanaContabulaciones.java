package com.example.nicolas.clientefinalandroid2.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab1;
import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;


public class VentanaContabulaciones extends TabActivity {

    private TabHost tabHost;
    private ArrayList<TabHost.TabSpec> arrPestañas;
    private TabHost.TabSpec tab1,tab2,tab3;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_con_tabulaciones);

        Intent intentRecibido = getIntent();
        int tabALaQueTengoQueIrAlIniciar = (Integer) intentRecibido.getSerializableExtra("tab?");
        tabALaQueTengoQueIrAlIniciar = tabALaQueTengoQueIrAlIniciar -1 ;

        tabHost = this.getTabHost();
        arrPestañas = new ArrayList<TabHost.TabSpec>();

        //PESTAÑA 1:
        tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("Ingresar 5 Numeros");
        Intent intent1 = new Intent(this,  Tab1.class);
        tab1.setContent(intent1);
        arrPestañas.add(tab1);
        this.tabHost.addTab(tab1);

        //PESTAÑA 2:
        tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("Seleccionar 5 Sueños:");
        Intent intent2 = new Intent(this,  com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab2.class);
        tab2.setContent(intent2);
        arrPestañas.add(tab2);
        this.tabHost.addTab(tab2);

        //PESTAÑA 3:
        tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("Tarjeta Saldo");
        Intent intent3 = new Intent(this,  com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab3.class);
        tab3.setContent(intent3);
        arrPestañas.add(tab3);
        this.tabHost.addTab(tab3);

        this.getCurrentTabHost().setCurrentTab(tabALaQueTengoQueIrAlIniciar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabed, menu);
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
    public TabHost getCurrentTabHost()
    {
        return this.tabHost;
    }
}
