package cliente;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializable.ConjuntoDevuelto;
import serializable.ConjuntoJugadas;
import serializable.Jugada;
import serializable.*;

public class ManejadorCliente  extends Application implements Serializable
{   
    //private final String direccionIPServer = XMLAPI.XMLHandler.leer("config.xml","direccionIP");
    //private final static String direccionIPServer = "10.0.2.2";
    private final static String direccionIPServer = "192.168.5.189";
    //private final int puertoServer = XMLAPI.XMLHandler.leerInt("config.xml","port");
    private final static int puertoServer = 9999;
    private static Cliente cliente;

    private static ParametrosEncapsuladosParaClientes pepc;
    private static ConjuntoJugadas conjuntoJugadasActuales = new ConjuntoJugadas();
    private static ConjuntoDevuelto conjuntoDevuelto;
    private static Tarjeta tarjetaActual;
    private static int maximoJugadas = 5;


    public static void enviarJugadasTest()
    {
        //A MODO DE PRUEBA, NO ANDARR CARGANDO COSAS:
        conjuntoJugadasActuales = new ConjuntoJugadas();
            Jugada j1 = new Jugada("1",50); conjuntoJugadasActuales.agregarJugada(j1,1);
            Jugada j2 = new Jugada("2",50); conjuntoJugadasActuales.agregarJugada(j2,2);
            Jugada j3 = new Jugada("3",50); conjuntoJugadasActuales.agregarJugada(j3,3);
            Jugada j4 = new Jugada("4",50); conjuntoJugadasActuales.agregarJugada(j4,4);
            Jugada j5 = new Jugada("5",50); conjuntoJugadasActuales.agregarJugada(j5,5);
        conjuntoDevuelto = new ConjuntoDevuelto();
        conjuntoDevuelto = enviarConjuntoJugadasAlServer(conjuntoJugadasActuales);
        
        System.out.println("EXTRACTO:\n" + conjuntoDevuelto.toString());
    }
    public static ConjuntoDevuelto enviarConjuntoJugadasAlServer()
    {
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();

        try
        {
            //ABRO CONEXION CON EL SERVER:
            cliente = new Cliente(direccionIPServer, puertoServer);
            cliente.start();

            //ENVIO BIT DE ESTADO DE LA CONEXION:
            cliente.enviar(2);//ESTADO DE LA CONEXION.
            cliente.join();

            //ENVIO LAS JUGADAS HECHAS:
            cliente.enviar(conjuntoJugadasActuales);
            cliente.join();

            //RECIBO EL RESULTADO DE LAS MISMAS:
            conjuntoDevuelto = (ConjuntoDevuelto) cliente.recibir();
            cliente.join();

            //CIERRO CONEXION CON EL SERVER:
            cliente.cerrar();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //SETEO EL CONJUNTO DEVUELTO AL MANEJADOR.
        setConjuntoDevuelto(conjuntoDevuelto);

        return conjuntoDevuelto;
    }
    public static void vaciarConjuntoJugadas()
    {
        conjuntoJugadasActuales = new ConjuntoJugadas();
    }
    public static ConjuntoDevuelto enviarConjuntoJugadasAlServer(ConjuntoJugadas conjuntoJugadas)
    {
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();
        
        try
        {
            //ABRO CONEXION CON EL SERVER:
            cliente = new Cliente(direccionIPServer, puertoServer);
            cliente.start();
            
            //ENVIO BIT DE ESTADO DE LA CONEXION:
            cliente.enviar(2);//ESTADO DE LA CONEXION.
            cliente.join();
            
            //ENVIO LAS JUGADAS HECHAS:
            cliente.enviar(conjuntoJugadas);
            cliente.join();
            
            //RECIBO EL RESULTADO DE LAS MISMAS:
            conjuntoDevuelto = (ConjuntoDevuelto) cliente.recibir();
            cliente.join();
            
            //CIERRO CONEXION CON EL SERVER:
            cliente.cerrar();
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conjuntoDevuelto;
    }
    public static ParametrosEncapsuladosParaClientes pedirParametrosAlServer()
    {
        try
        {
            ///ABRO CONEXION CON EL SERVER Y ESTABLESCO CANALES I/O:
            cliente = new Cliente(direccionIPServer, puertoServer);
            cliente.start();
            cliente.join();
            
            //ENVIO BIT DE ESTADO DE LA CONEXION:
            cliente.enviar(1);  //ESTADO DE LA CONEXION.
            cliente.join();
            
            //RECIBO LOS PARAMETROS DEL SERVER:
            pepc = (ParametrosEncapsuladosParaClientes) cliente.recibir();
            cliente.join();
            
            if (pepc != null)
            {
                System.out.println("" +  pepc.toString());
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
    public static void agregarJugadaAlConjunto(Jugada jugada,int posicion)
    {
        conjuntoJugadasActuales.agregarJugada(jugada,posicion);
    }





    /*GYS*/
    public static int getPuertoServer() {
        return puertoServer;
    }
    public static Cliente getCliente() {
        return cliente;
    }
    public static void setCliente(Cliente cliente) {
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
