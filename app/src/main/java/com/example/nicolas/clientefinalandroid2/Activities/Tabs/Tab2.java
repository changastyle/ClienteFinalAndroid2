package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.nicolas.clientefinalandroid2.R;

import Controller.ManejadorCliente;

public class Tab2 extends ActionBarActivity implements View.OnClickListener{

    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);

        gridLayout = (GridLayout) findViewById(R.id.gridLayoutTab2);

        for(int i = 0 ; i < 100; i++)
        {
            ImageButton imageButton = new ImageButton(this.getBaseContext());
            imageButton.setBackgroundResource(R.drawable.huevos);
            imageButton.setOnClickListener(this);
            imageButton.setPadding(10,10,10,10);


            gridLayout.addView(imageButton);
        }

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
        //Button botonPresionado = (Button) v;


//        v.setBackgroundColor(R.color.botonVerdeInicial);
 //       if(botonPresionado ==)
    }
}
