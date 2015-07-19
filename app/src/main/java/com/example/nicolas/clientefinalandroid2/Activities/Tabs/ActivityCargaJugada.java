package com.example.nicolas.clientefinalandroid2.Activities.Tabs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.clientefinalandroid2.R;

import java.util.ArrayList;

import cliente.ManejadorCliente;
import serializable.Jugada;

public class ActivityCargaJugada extends ActionBarActivity implements View.OnClickListener {

    ArrayList<Button> arrBotones;
    Button but0,but1,but2,but3,but4,but5,but6,but7,but8,but9;
    Button butMas,butMenos,butOk,butCancel,butBorrar;
    TextView tv1,tv2,tvPrecio;
    int precio;
    int indiceJugadaActual;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_jugada);

        Intent intentRecibido = getIntent();
        indiceJugadaActual = (int) intentRecibido.getSerializableExtra("jugada");

        //SETEO EL TITULO:
        this.setTitle("Jugada " + (indiceJugadaActual + 1) + " de 5");



        precio = 1;

        arrBotones = new ArrayList<Button>();
        but0 = (Button) findViewById(R.id.but0);arrBotones.add(but0);
        but1 = (Button) findViewById(R.id.but1);arrBotones.add(but1);
        but2 = (Button) findViewById(R.id.but2);arrBotones.add(but2);
        but3 = (Button) findViewById(R.id.but3);arrBotones.add(but3);
        but4 = (Button) findViewById(R.id.but4);arrBotones.add(but4);
        but5 = (Button) findViewById(R.id.but5);arrBotones.add(but5);
        but6 = (Button) findViewById(R.id.but6);arrBotones.add(but6);
        but7 = (Button) findViewById(R.id.but7);arrBotones.add(but7);
        but8 = (Button) findViewById(R.id.but8);arrBotones.add(but8);
        but9 = (Button) findViewById(R.id.but9);arrBotones.add(but9);

        butMas = (Button)findViewById(R.id.butMas);butMas.setOnClickListener(this);
        butMenos = (Button)findViewById(R.id.butMenos);butMenos.setOnClickListener(this);
        butOk = (Button)findViewById(R.id.butOK);butOk.setOnClickListener(this);
        butCancel = (Button)findViewById(R.id.butCancel);butCancel.setOnClickListener(this);
        butBorrar = (Button)findViewById(R.id.butBorrar);butBorrar.setOnClickListener(this);;

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);

        agregarListeners();
    }
    private void agregarListeners()
    {
        for(Button b : arrBotones)
        {
            b.setOnClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab1, menu);
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

    @Override
    public void onClick(View v)
    {
        Button b = (Button)v;

        //PARA LOS NUMEROS:
        for(Button but : arrBotones)
        {
            if (but == b)
            {
                if(tv1.getText().toString().equalsIgnoreCase(getResources().getString(R.string.tv1Inicial)))
                {
                    tv1.setText("");
                }
                if( tv1.getText().toString().length() < 3)
                {
                    tv1.setText(tv1.getText().toString() + "" + b.getText().toString());
                }
                else
                {
                    Toast.makeText(this,"No se puede un numero mayor a 1000",Toast.LENGTH_SHORT).show();
                }
                dameSuenio();
            }
        }

        if(b == butMas)
        {
            if(precio < 10)
            {
                precio ++;
            }
        }
        if(b == butMenos)
        {
            if(precio > 1)
            {
                precio --;
            }
        }
        if(b == butCancel)
        {
            precio = 1;
            tv1.setText(R.string.tv1Inicial);

        }
        if(b == butOk)
        {
            String msg = "";
            if(tv1.getText().toString().equalsIgnoreCase("Numero Apostado"))
            {
                msg ="No apostaste a un numero valido..";
            }
            else
            {
                msg ="Jugada valida!";

                String numeroApostado = tv1.getText().toString();
                int dineroApostado = 5;
                Jugada jugada =  new Jugada(numeroApostado,dineroApostado);

                ManejadorCliente.agregarJugadaAlConjunto(jugada,this.indiceJugadaActual);


                Intent i = new Intent(this,Tab1.class);
                startActivity(i);
            }
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        }
        if(b == butBorrar)
        {
            if(tv1.getText().toString().length()==1 || tv1.getText().toString().equalsIgnoreCase(getResources().getString(R.string.tv1Inicial)))
            {
                tv1.setText(R.string.tv1Inicial);
                tv2.setText("");
            }
            else
            {
                tv1.setText(tv1.getText().toString().substring(0,tv1.getText().toString().length() -1 ));
                dameSuenio();
            }
        }
        tvPrecio.setText("$" + precio + ".00");
    }
    private void dameSuenio()
    {

        String strNumeroActual = tv1.getText().toString();

        if(strNumeroActual.length()>2)
        {
            strNumeroActual = strNumeroActual.substring(1, 3);
            //System.out.println("STRNUMEROACUTIAL:" + strNumeroActual);
        }
        int numeroActual = Integer.parseInt(strNumeroActual);
        String arrSuenios[] = {"Huevos","Agua", "Niño" ,"San Cono","La Cama",
                "Gato" ,  "Perro" , "Revolver" , "Incendio" , "Arroyo" , "La leche" , "Palito" , "Soldado" ,"La yeta" , "Borracho" , "Niña bonita" , "Anillo",
                "Desgracia" ,  "Sangre" , "Pescado" , "La fiesta" , "La mujer", "El loco",  "Mariposa" , "Caballo" ,  "Gallina" , "La misa" ,  "El peine", "El cerro" ,  "San Pedro" , "Santa Rosa"  , "La luz" , "Dinero" , "Cristo" ,"Cabeza", "Pajarito", "Manteca" ,  "Dentista" ,  "Aceite" ,  "Lluvia" , "Cura" ,  "Cucho", "Zapatilla" , "Balcón" , "La cárcel" ,  "El vino" , "Tomates", "Muerto", "Muerto habla" , "La carne" , "El pan", "Serrucho" ,"Madre" ,"El barco" ,"La vaca", "Los gallegos", "La caída","Jorabajo" , "Ahogado", "Planta" , "Virgen" , "Escopeta" , "Inundacion", "Casamiento" , "Llanto", "Cazador" , "Lombrices" , "Víbora" ,"Sobrinos", "Vicios" , "Muerto sueño", "Excrementos", "Sorpresa", "Hospital" ,"Negros" , "Payaso",  "Llamas" ,"Las piernas", "Ramera" , "Ladrón" , "La bocha" , "Flores" , "Pelea" ,"Mal tiempo", "Iglesia", "Linterna", "Humo", "Piojos", "El Papa" ,"La rata" , "El miedo" , "Excusado", "Médico", "Enamorado" , "Cementerio", "Anteojos", "Marido", "La mesa","Lavandera","Hermanos" };

        tv2.setText(String.valueOf(arrSuenios[numeroActual]));
    }
}
