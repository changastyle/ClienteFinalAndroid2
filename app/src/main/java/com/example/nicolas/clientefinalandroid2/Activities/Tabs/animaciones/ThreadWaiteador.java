package com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones;

/**
 * Created by nicolas on 28/07/15.
 */
public class ThreadWaiteador extends Thread
{
    private Transparente t;
    public ThreadWaiteador(Transparente t)
    {
        this.t = t;
    }
    public void run()
    {
        try
        {
            this.sleep(1000);
            t.finish();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
