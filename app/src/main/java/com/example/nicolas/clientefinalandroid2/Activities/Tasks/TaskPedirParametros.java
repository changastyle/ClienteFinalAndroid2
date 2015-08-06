package com.example.nicolas.clientefinalandroid2.Activities.Tasks;

import android.os.AsyncTask;

import com.example.nicolas.clientefinalandroid2.Activities.ActivityCargaBarra;

import Controller.ManejadorCliente;
import serializable.ParametrosEncapsuladosParaClientes;

public class TaskPedirParametros extends AsyncTask
{
    private ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes;
    private ActivityCargaBarra activityCargaBarra;

    public TaskPedirParametros(ActivityCargaBarra activityCargaBarra)
    {
        this.activityCargaBarra = activityCargaBarra;
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        parametrosEncapsuladosParaClientes = ManejadorCliente.pedirParametrosAlServer();
        ManejadorCliente.setPepc(parametrosEncapsuladosParaClientes);

        return parametrosEncapsuladosParaClientes;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        if (o != null)
        {
            this.activityCargaBarra.listo();
        }
        else
        {
            this.activityCargaBarra.noListo();
        }
    }
}
