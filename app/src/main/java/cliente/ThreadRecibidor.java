package cliente;

import java.io.ObjectInputStream;

class ThreadRecibidor extends Thread
{
    private ObjectInputStream in ;
    private Object objetoRecibido;
    
    public ThreadRecibidor(ObjectInputStream in)
    {
        this.in = in ;
    }
    public void run()
    {
        try
        {
            objetoRecibido = in.readObject();
        } 
        catch (Exception e)
        {
            System.out.println("ERROR: recibiendo :" + e.toString() );
        }
    }
    
    
    /*GYS*/

    public ObjectInputStream getIn()
    {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }

    public Object getObjetoRecibido()
    {
        return objetoRecibido;
    }

    public void setObjetoRecibido(Object objetoRecibido)
    {
        this.objetoRecibido = objetoRecibido;
    }
    
}
