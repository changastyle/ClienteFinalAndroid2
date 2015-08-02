package clienteNicoExpress.cliente;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.MenuItem;

import com.example.nicolas.clientefinalandroid2.R;

import java.io.Serializable;
import java.util.Objects;

import serializable.ConjuntoDevuelto;
import serializable.ConjuntoJugadas;
import serializable.Jugada;
import serializable.*;

public class ManejadorCliente implements Serializable
{   
    //private final String direccionIPServer = clienteNicoExpress.XMLAPI.XMLHandler.leer("config.xml","direccionIP");
    //private final static String direccionIPServer = "10.0.2.2";
    //private final static String direccionIPServer = "192.168.5.189";
    private final static String direccionIPServer = "104.236.220.19";
    //private final int puertoServer = clienteNicoExpress.XMLAPI.XMLHandler.leerInt("config.xml","port");
    private final static int puertoServer = 9999;
    private static ClienteGenerico cliente;

    private static ParametrosEncapsuladosParaClientes pepc;
    private static ConjuntoJugadas conjuntoJugadasActuales = new ConjuntoJugadas();
    private static ConjuntoDevuelto conjuntoDevuelto;
    private static Tarjeta tarjetaActual;
    private static int maximoJugadas = 5;



    public static ParametrosEncapsuladosParaClientes pedirParametrosAlServer()
    {
        try
        {
            ///ABRO CONEXION CON EL SERVER Y ESTABLESCO CANALES I/O:
            cliente = new ClienteGenerico(direccionIPServer, puertoServer);
            cliente.start();

            //ENVIO BIT DE ESTADO DE LA CONEXION:
            cliente.enviar(1);  //ESTADO DE LA CONEXION.

            //RECIBO LOS PARAMETROS DEL SERVER:
            pepc = (ParametrosEncapsuladosParaClientes) cliente.recibir();

            if (pepc != null)
            {
                System.out.println("RECIBI PEPC:" +  pepc.toString());
            }
            else
            {
                System.out.println("RECIBI PEPC = NULL.");
            }

            //CIERRO LA CONEXION CON EL SERVER:
            cliente.cerrar();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: NO SE PUDO PEDIR PARAMETROS AL SERVIDOR.");
            e.printStackTrace();
        }
        return pepc;
    }
    public static Tarjeta pedirDatosDeLaTarjeta(int numeroTarjeta)
    {
        Tarjeta respuesta = null;

        cliente = new ClienteGenerico(direccionIPServer,puertoServer);
        cliente.start();
        cliente.enviar(3);
        cliente.enviar(numeroTarjeta);
        boolean existe = (boolean) cliente.recibir();
        if(existe)
        {
            respuesta = (Tarjeta) cliente.recibir();
            tarjetaActual =  respuesta;
        }
        else
        {
            respuesta = null;
            tarjetaActual = new Tarjeta();
        }
        cliente.cerrar();

        return respuesta;
    }
    public static void agregarJugadaAlConjunto(Jugada jugada,int posicion)
    {
        conjuntoJugadasActuales.agregarJugada(jugada, posicion);
    }
    public static void vaciarConjuntoJugadas()
    {
        conjuntoJugadasActuales = new ConjuntoJugadas();
    }
    public static void enviarJugadasTest()
    {
        //A MODO DE PRUEBA, NO ANDARR CARGANDO COSAS:
        conjuntoJugadasActuales = new ConjuntoJugadas();

        for (int i = 0 ; i < maximoJugadas ; i++)
        {
            Jugada jugadaProvisoria = new Jugada("" + ( i + 1) ,50);
            conjuntoJugadasActuales.agregarJugada(jugadaProvisoria, (i +1));
        }

        conjuntoDevuelto = enviarConjuntoJugadasAlServer(conjuntoJugadasActuales);

        if(conjuntoDevuelto != null)
        {
            System.out.println("EXTRACTO:\n" + conjuntoDevuelto.toString());
        }
        else
        {
            System.out.println("EXTRACTO: NULL" );
        }
    }
    public static ConjuntoDevuelto enviarConjuntoJugadasAlServer()
    {
        ConjuntoDevuelto conjuntoDevuelto = null;

        try
        {
            //ABRO CONEXION CON EL SERVER:
            cliente = new ClienteGenerico(direccionIPServer, puertoServer);
            cliente.start();

            //ENVIO BIT DE ESTADO DE LA CONEXION:
            cliente.enviar(2);//ESTADO DE LA CONEXION.

            //ENVIO LAS JUGADAS HECHAS:
            System.out.println("asdfasd:" +getTarjetaActual());
            conjuntoJugadasActuales.setTarjetaActual(getTarjetaActual());
            cliente.enviar(conjuntoJugadasActuales);

            //RECIBO EL RESULTADO DE LAS MISMAS:
            conjuntoDevuelto = (ConjuntoDevuelto) cliente.recibir();

            //CIERRO CONEXION CON EL SERVER:
            cliente.cerrar();

            //SETEO EL CONJUNTO DEVUELTO AL MANEJADOR.
            if(conjuntoDevuelto != null)
            {
                setConjuntoDevuelto(conjuntoDevuelto);
                ManejadorCliente.setTarjetaActual(conjuntoDevuelto.getTarjetaDevuelta());
            }
            else
            {
                System.out.println("ERROR: CONJUNTO DEVUELTO = NULL");
                new ConjuntoDevuelto();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return conjuntoDevuelto;
    }
    public static ConjuntoDevuelto enviarConjuntoJugadasAlServer(ConjuntoJugadas conjuntoJugadas)
    {
        ConjuntoDevuelto conjuntoDevuelto = null;
        
        try
        {
            //ABRO CONEXION CON EL SERVER:
            cliente = new ClienteGenerico(direccionIPServer, puertoServer);
            cliente.start();
            
            //ENVIO BIT DE ESTADO DE LA CONEXION:
            cliente.enviar(2);//ESTADO DE LA CONEXION.
            
            //ENVIO LAS JUGADAS HECHAS:
            //conjuntoJugadas.setTarjetaActual(getTarjetaActual());
            cliente.enviar(conjuntoJugadas);
            
            //RECIBO EL RESULTADO DE LAS MISMAS:
            conjuntoDevuelto = (ConjuntoDevuelto) cliente.recibir();
            
            //CIERRO CONEXION CON EL SERVER:
            cliente.cerrar();

            if(conjuntoDevuelto != null)
            {
                return conjuntoDevuelto;
            }
            else
            {
                System.out.println("ERROR: CONJUNTO DEVUELTO = NULL");
                conjuntoDevuelto = new ConjuntoDevuelto();
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conjuntoDevuelto;
    }


    public static boolean menuOperaciones(MenuItem item,Activity activityActual)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.contacto:

                Intent intentToContato = new Intent( activityActual ,com.example.nicolas.clientefinalandroid2.Activities.Tabs.Tab4.class);
                activityActual.startActivity(intentToContato);

                return true;
            case R.id.exit:
                System.exit(0);
                return true;
            case R.id.configuracion:
                System.out.println("Configuracion");
                return true;
            default:
                return true;
        }
    }



    /*GYS*/
    public static int getPuertoServer() {
        return puertoServer;
    }
    public static ClienteGenerico getCliente() {
        return cliente;
    }
    public static void setCliente(ClienteGenerico cliente) {
        ManejadorCliente.cliente = cliente;
    }
    public static ConjuntoDevuelto getConjuntoDevuelto() {
        return conjuntoDevuelto;
    }
    public static void setConjuntoDevuelto(ConjuntoDevuelto conjuntoDevuelto) {
        ManejadorCliente.conjuntoDevuelto = conjuntoDevuelto;
    }
    public static ParametrosEncapsuladosParaClientes getPepc() {
        return pepc;
    }
    public static void setPepc(ParametrosEncapsuladosParaClientes pepc){
        ManejadorCliente.pepc = pepc;
    }
    public static ConjuntoJugadas getConjuntoJugadasActuales() {
        return conjuntoJugadasActuales;
    }
    public static void setConjuntoJugadasActuales(ConjuntoJugadas conjuntoJugadasActuales) {
        ManejadorCliente.conjuntoJugadasActuales = conjuntoJugadasActuales;
    }
    public static String getDireccionIPServer()
    {
        return direccionIPServer;
    }
    public static Tarjeta getTarjetaActual()
    {
        return tarjetaActual;
    }
    public static void setTarjetaActual(Tarjeta tarjetaActual)
    {
        ManejadorCliente.tarjetaActual = tarjetaActual;
    }
    public static int getMaximoJugadas()
    {
        return maximoJugadas;
    }
    public static void setMaximoJugadas(int maximoJugadas)
    {
        ManejadorCliente.maximoJugadas = maximoJugadas;
    }
}
