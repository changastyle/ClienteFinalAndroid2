
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread
{
    private String direccionIP;
    private int puerto;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ThreadRecibidor threadRecibidor;
    private ThreadEnviador threadEnviador;
    
    public Cliente(String direccionIP, int puerto)
    {
        this.direccionIP = direccionIP;
        this.puerto = puerto;
        socket = null;
            
    }
    
    public void run()
    {
        try
        {
             socket = new Socket(direccionIP, puerto);
             in = new ObjectInputStream(socket.getInputStream());
             out = new ObjectOutputStream(socket.getOutputStream());
             if(in != null)
             {
                 System.out.println("CANAL IN ESTABLECIDO");
             }
             if(out != null)
             {
                 System.out.println("CANAL OUT ESTABLECIDO");
             }
             
        } catch (Exception e)
        {
            System.out.println("ERROR:" +  e.toString());
            e.printStackTrace();
        }
    }
    public void enviar(Object object)
    {
        try
        {
            this.join();

            threadEnviador = new ThreadEnviador(out,object);
            threadEnviador.start();
            threadEnviador.join();
        } 
        catch (Exception e)
        {
            System.out.println("ERROR: OUT NO ESTABLECIDO " + e.toString());
        }
        
    }
    
    public Object recibir()
    {
        Object objectoRecibido = null;
        
        try
        {
            threadRecibidor = new ThreadRecibidor(in);
            threadRecibidor.start();
            threadRecibidor.join();
            objectoRecibido = threadRecibidor.getObjetoRecibido();
            
            if(objectoRecibido != null)
            {
                //System.out.println("" + objectoRecibido);
            } 
        } 
        catch (InterruptedException ex)
        {
            System.out.println("ERROR: JOIN RECIBIR -> " + ex.toString() );
        }
        
        return objectoRecibido;
    }
    /*GYS*/

    public String getDireccionIP()
    {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP)
    {
        this.direccionIP = direccionIP;
    }

    public int getPuerto()
    {
        return puerto;
    }

    public void setPuerto(int puerto)
    {
        this.puerto = puerto;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    public ObjectInputStream getIn()
    {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }

    public ObjectOutputStream getOut()
    {
        return out;
    }

    public void setOut(ObjectOutputStream out)
    {
        this.out = out;
    }

    void cerrar() 
    {
        try 
        {
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
   
    }
    
    
}
