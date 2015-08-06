package com.example.nicolas.clientefinalandroid2.Activities.Tasks;

import android.os.AsyncTask;

import com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones.LoadingActivityWaitingForTarjetaFromQR;

import Controller.ManejadorCliente;
import serializable.Tarjeta;

/**
 * Created by NICOLAS on 06/08/2015.
 */
public class TaskPedirDatosTarjeta extends AsyncTask
{
    private LoadingActivityWaitingForTarjetaFromQR loadingActivityWaitingForTarjetaFromQR;
    private int numeroEscaneadoConElQR;
    public TaskPedirDatosTarjeta(LoadingActivityWaitingForTarjetaFromQR loadingActivityWaitingForTarjetaFromQR)
    {
        this.loadingActivityWaitingForTarjetaFromQR = loadingActivityWaitingForTarjetaFromQR;
        numeroEscaneadoConElQR = loadingActivityWaitingForTarjetaFromQR.getNumeroEscaneadoConElQR();
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        Tarjeta tarjetaActual = ManejadorCliente.pedirDatosDeLaTarjeta(numeroEscaneadoConElQR);
        ManejadorCliente.setTarjetaActual(tarjetaActual);

        return tarjetaActual;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        this.loadingActivityWaitingForTarjetaFromQR.listo();
    }
}
