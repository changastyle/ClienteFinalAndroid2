package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ThreadEnviador extends Thread
{
    public ObjectOutputStream out;
    private Object object;
    
    public ThreadEnviador(ObjectOutputStream out,Object object)
    {
        this.out = out ;
        this.object = object;
    }
    public void run()
    {
        try
        {
            this.getOut().writeObject(this.getObject());
        }
        catch (Exception e)
        {
            System.out.println("ERROR: Al enviar " + object.toString() + " ->" + e.toString() );
        }
    }
    
    /**/

    public ObjectOutputStream getOut()
    {
        return out;
    }

    public void setOut(ObjectOutputStream out)
    {
        this.out = out;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
    
}
