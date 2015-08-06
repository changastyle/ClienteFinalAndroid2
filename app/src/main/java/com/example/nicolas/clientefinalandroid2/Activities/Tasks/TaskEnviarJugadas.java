package com.example.nicolas.clientefinalandroid2.Activities.Tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.nicolas.clientefinalandroid2.Activities.Tabs.ActivityResultados;
import com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab1;
import com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab2;

import serializable.ConjuntoDevuelto;

/**
 * Created by NICOLAS on 06/08/2015.
 */
public class TaskEnviarJugadas extends AsyncTask
{
    private Tab1 tab1;
    private ActivityResultados tab2;
    private boolean usoTab1;
    public TaskEnviarJugadas(Tab1 tab1)
    {
        this.tab1 = tab1;
        usoTab1 = true;
    }
    public TaskEnviarJugadas(ActivityResultados tab2)
    {
        this.tab2 = tab2;
        usoTab1 = false;
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        ConjuntoDevuelto conjuntoDevuelto = Controller.ManejadorCliente.enviarConjuntoJugadasAlServer();
        return conjuntoDevuelto;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        if(usoTab1)
        {
            tab1.listo();
        }
        else
        {
            tab2.listo();
        }
    }
}
