package Threads;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nicolas.clientefinalandroid2.Activities.ActivityCargaBarra;

/**
 * Created by nicolas on 16/07/15.
 */
public class ThreadBarraStatusWelcomeActivity extends Thread
{
    int progreso = 0;
    private ActivityCargaBarra activityWelcome;
    private ProgressBar progressBar;
    private ImageView imagenTrebol;

    public ThreadBarraStatusWelcomeActivity(ActivityCargaBarra activityWelcome)
    {
        progressBar = activityWelcome.getBarra();
        this.activityWelcome = activityWelcome;

    }
    public void run()
    {
        try
        {
            while(true)
            {
                progreso += 10;
                progressBar.setProgress(progreso);
                //activityWelcome.rotarTrebol(progreso);
                Thread.sleep(300);

                if(progreso == 100)
                {
                    activityWelcome.pasarActivityPrincipal();
                    this.interrupt();
                    break;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
