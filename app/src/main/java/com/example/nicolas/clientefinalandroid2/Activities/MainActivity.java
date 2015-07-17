package com.example.nicolas.clientefinalandroid2.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import cliente.*;
import serializable.ParametrosEncapsuladosParaClientes;

import com.example.nicolas.clientefinalandroid2.R;

import org.apache.http.client.ClientProtocolException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread welcomeThread = new Thread()
        {
            public void run()
            {
                try
                {
                    System.out.println("VOY A PEDIR DATOS.");
                    Cliente c = new Cliente("10.0.2.2", 9999);
                    c.start();
                    c.join();
                    c.enviar(1);
                    ParametrosEncapsuladosParaClientes pepc = (ParametrosEncapsuladosParaClientes) c.recibir();
                    System.out.println(pepc.toString());
                    System.out.println("TERMINE DE PEDIR DATOS.");


                    //Thread.sleep(2000);
                    Intent i = new Intent(MainActivity.this, TabedActivity.class);
                    startActivity(i);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        welcomeThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
