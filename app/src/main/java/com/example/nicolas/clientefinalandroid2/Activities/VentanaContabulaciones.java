package com.example.nicolas.clientefinalandroid2.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.nicolas.clientefinalandroid2.Activities.Tabs.ActivityResultados;
import com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab1;
import com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab3;
import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import Controller.ManejadorCliente;


public class VentanaContabulaciones extends TabActivity
{

    private TabHost tabHost;
    private ArrayList<TabHost.TabSpec> arrPestañas;
    private TabHost.TabSpec tab1,tab2,tab3,tab4;
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
        tab1.setIndicator(getResources().getString(R.string.TextoIndicadorTab1));
        Intent intent1 = new Intent(this,  Tab1.class);
        tab1.setContent(intent1);
        arrPestañas.add(tab1);
        this.tabHost.addTab(tab1);

        //PESTAÑA 2:
        tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator(getResources().getString(R.string.TextoIndicadorTab2));
        Intent intent2 = new Intent(this, ActivityResultados.class);
        tab2.setContent(intent2);
        arrPestañas.add(tab2);
        this.tabHost.addTab(tab2);

        //PESTAÑA 3:
        tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator(getResources().getString(R.string.TextoIndicadorTab3));
        Intent intent3 = new Intent(this,  Tab3.class);
        tab3.setContent(intent3);
        arrPestañas.add(tab3);
        this.tabHost.addTab(tab3);

        //PESTAÑA 4:
        /*tab4 = tabHost.newTabSpec("tab4");
        tab4.setIndicator(getResources().getString(R.string.TextoIndicadorTab4));
        Intent intent4 = new Intent(this,  com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab4.class);
        tab4.setContent(intent4);
        arrPestañas.add(tab4);
        this.tabHost.addTab(tab4);*/

        this.getCurrentTabHost().setCurrentTab(tabALaQueTengoQueIrAlIniciar);
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
    public TabHost getCurrentTabHost()
    {
        return this.tabHost;
    }
}
