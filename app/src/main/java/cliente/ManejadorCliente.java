package cliente;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializable.ConjuntoDevuelto;
import serializable.ConjuntoJugadas;
import serializable.Jugada;
import serializable.*;

public class ManejadorCliente
{   
    //private final String direccionIPServer = XMLAPI.XMLHandler.leer("config.xml","direccionIP");
    private final String direccionIPServer = "localhost";
    //private final int puertoServer = XMLAPI.XMLHandler.leerInt("config.xml","port");
    private final int puertoServer = 9999;
    private Cliente cliente;
    private ConjuntoJugadas conjuntoJugadas;
    private ConjuntoDevuelto conjuntoDevuelto;
    private ParametrosEncapsuladosParaClientes pepc;
    
    public ManejadorCliente()
    {
        System.out.println("DIRECCION IP DEL SERVER: " + direccionIPServer);
        System.out.println("PORT DEL SERVER: " +  puertoServer);

        //pepc = pedirParametrosAlServer();
        
        //VentanaCliente ventanaCliente = new VentanaCliente(this);
        
        //enviarJugadasTest(); 
    }
    public void enviarJugadasTest()
    {
        //A MODO DE PRUEBA, NO ANDARR CARGANDO COSAS:
        conjuntoJugadas = new ConjuntoJugadas();
            Jugada j1 = new Jugada("1",50); conjuntoJugadas.agregarJugada(j1);
            Jugada j2 = new Jugada("2",50); conjuntoJugadas.agregarJugada(j2);
            Jugada j3 = new Jugada("3",50); conjuntoJugadas.agregarJugada(j3);
            Jugada j4 = new Jugada("4",50); conjuntoJugadas.agregarJugada(j4);
            Jugada j5 = new Jugada("5",50); conjuntoJugadas.agregarJugada(j5);
        conjuntoDevuelto = new ConjuntoDevuelto();
        conjuntoDevuelto = enviarConjuntoJugadasAlServer(conjuntoJugadas);
        
        System.out.println("EXTRACTO:\n" + conjuntoDevuelto.toString());
    }
    public void enviarJugadas(ConjuntoJugadas conjuntoJugadas)
    {
        conjuntoDevuelto = new ConjuntoDevuelto();
        conjuntoDevuelto = enviarConjuntoJugadasAlServer(conjuntoJugadas);
        
        System.out.println("EXTRACTO:\n" + conjuntoDevuelto.toString());
    }
    public ConjuntoDevuelto enviarConjuntoJugadasAlServer(ConjuntoJugadas conjuntoJugadas)
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
    public ParametrosEncapsuladosParaClientes pedirParametrosAlServer()
    {
        ParametrosEncapsuladosParaClientes pepcAUX = new ParametrosEncapsuladosParaClientes();
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
            pepcAUX = (ParametrosEncapsuladosParaClientes) cliente.recibir();
            cliente.join();
            
            if (pepcAUX != null)
            {
                System.out.println("" +  pepcAUX.toString());
            }
            
            //CIERRO LA CONEXION CON EL SERVER:
            cliente.cerrar();
        } 
        catch (Exception e)
        {
            System.out.println("ERROR: NO SE PUDO PEDIR PARAMETROS AL SERVIDOR.");
            e.printStackTrace();
        }
        return pepcAUX;
    }
    public void agregarJugadaAlConjunto(Jugada jugada)
    {
        this.conjuntoJugadas.agregarJugada(jugada);
    }
    
    /*GYS*/
    public Cliente getCliente() 
    {
        return cliente;
    }
    public void setCliente(Cliente cliente) 
    {
        this.cliente = cliente;
    }
    public ConjuntoJugadas getConjuntoJugadas() 
    {
        return conjuntoJugadas;
    }
    public void setConjuntoJugadas(ConjuntoJugadas conjuntoJugadas)
    {
        this.conjuntoJugadas = conjuntoJugadas;
    }
    public ConjuntoDevuelto getConjuntoDevuelto() 
    {
        return conjuntoDevuelto;
    }
    public void setConjuntoDevuelto(ConjuntoDevuelto conjuntoDevuelto) 
    {
        this.conjuntoDevuelto = conjuntoDevuelto;
    }
    public ParametrosEncapsuladosParaClientes getPepc() 
    {
        return pepc;
    }
    public void setPepc(ParametrosEncapsuladosParaClientes pepc) 
    {
        this.pepc = pepc;
    }  
}
