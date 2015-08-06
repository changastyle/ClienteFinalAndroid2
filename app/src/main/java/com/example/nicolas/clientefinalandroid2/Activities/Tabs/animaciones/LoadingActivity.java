package com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.clientefinalandroid2.R;


public class LoadingActivity extends Activity {

    private MediaPlayer mp;
    private ImageView imagen;
    private TextView tvTransparente;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        tvTransparente = (TextView) findViewById(R.id.tvTransparente);
        imagen = (ImageView) findViewById(R.id.imagen);
        /*String cual = getIntent().getSerializableExtra("cual").toString();

        if(cual.equalsIgnoreCase("exito"))
        {
            imagen.setBackgroundResource(R.drawable.tilde);
            mp = MediaPlayer.create(this, R.raw.exito);
            tvTransparente.setText("GANASTEEE !!!");
        }
        else
        {
            imagen.setBackgroundResource(R.drawable.cruz);
            mp = MediaPlayer.create(this, R.raw.fracaso);
            tvTransparente.setText("NO GANADOR");
        }

        mp.start();

        ThreadWaiteador threadWaiteador = new ThreadWaiteador(this);
        threadWaiteador.start();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transparente, menu);
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
