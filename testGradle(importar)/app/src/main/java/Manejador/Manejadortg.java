package Manejador;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by NICOLAS on 11/07/2015.
 */
public class Manejador
{

    public static String obtenerSuenio(View v)
    {
        EditText editTextAux = (EditText) v;

        int numeroValido = convertirANumeroValido(editTextAux);
        editTextAux.setText(String.valueOf(numeroValido));
        String suenio = pedirSuenoANumero(numeroValido);

        return suenio;
    }


    public static String pedirSuenoANumero(int numeroValido)
    {
        String salida ="";

        String arr[] = {"huevos","azucar","mais","bro","lentejas","perro","gato","salcicha","pollo"};


        if((numeroValido-1) <= arr.length && numeroValido > 0)
        {
            salida = arr[numeroValido];
        }
        else
        {
            salida = "patatas";
        }

        return salida;
    }

    public static int convertirANumeroValido(EditText et)
    {
        int salida = 0;

        String textoActual = et.getText().toString();

        if (textoActual.length() > 3)
        {
            textoActual = textoActual.substring(0,3);
        }

        //et.setText(textoActual);

        try
        {
            salida = Integer.parseInt(textoActual);
        }
        catch(Exception e)
        {
            salida = 0;
        }


        return salida;
    }
}
