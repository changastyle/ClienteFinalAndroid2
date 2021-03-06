package com.example.nicolas.clientefinalandroid2.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;


public class TabedActivity extends TabActivity {

    private TabHost tabHost;
    private ArrayList<TabHost.TabSpec> arrPestañas;
    private TabHost.TabSpec tab1,tab2,tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabed);

        tabHost = this.getTabHost();
        arrPestañas = new ArrayList<TabHost.TabSpec>();

        //PESTAÑA 1:
        tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("Ingresar 5 Numeros");
        tab1.setContent(new Intent(this, com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab1.class));
        arrPestañas.add(tab1);


        //PESTAÑA 2:
        tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("Seleccionar 5 Sueños:");
        tab2.setContent(new Intent(this,  com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab2.class));
        arrPestañas.add(tab2);

        //PESTAÑA 2:
        tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("Tarjeta Saldo");
        tab3.setContent(new Intent(this,  com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab3.class));
        arrPestañas.add(tab3);


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabed, menu);
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
}
