package com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.clientefinalandroid2.Activities.Tasks.TaskPedirDatosTarjeta;
import com.example.nicolas.clientefinalandroid2.R;


public class LoadingActivityWaitingForTarjetaFromQR extends Activity
{

    private MediaPlayer mp;
    private ImageView imagen;
    private TextView  textViewNumeroLeidoLoadingActivity;
    private int numeroEscaneadoConElQR;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        int numeroEscaneadoConElQR = (Integer)(getIntent().getSerializableExtra("numeroEscaneadoConElQR"));
        this.numeroEscaneadoConElQR = numeroEscaneadoConElQR;
        textViewNumeroLeidoLoadingActivity = (TextView) findViewById(R.id.textViewNumeroLeidoLoadingActivity);
        textViewNumeroLeidoLoadingActivity.setText(String.valueOf(numeroEscaneadoConElQR));
        imagen = (ImageView) findViewById(R.id.imagen);

        TaskPedirDatosTarjeta taskPedirDatosTarjeta = new TaskPedirDatosTarjeta(this);
        taskPedirDatosTarjeta.execute();

    }

    public void listo()
    {
        System.out.println("LOADING ACTIVITY FINISH");
        this.finish();
        /*Intent intentToLoadingActivity = new Intent(this,com.example.nicolas.clientefinalandroid2.Activities.VentanaContabulaciones.class);
        intentToLoadingActivity.putExtra("tab?",1);
        startActivity(intentToLoadingActivity);*/
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
        return Controller.ManejadorCliente.menuOperaciones(item, this);
    }

    /*GYS*/
    public int getNumeroEscaneadoConElQR()
    {
        return numeroEscaneadoConElQR;
    }

    public void setNumeroEscaneadoConElQR(int numeroEscaneadoConElQR)
    {
        this.numeroEscaneadoConElQR = numeroEscaneadoConElQR;
    }
}
