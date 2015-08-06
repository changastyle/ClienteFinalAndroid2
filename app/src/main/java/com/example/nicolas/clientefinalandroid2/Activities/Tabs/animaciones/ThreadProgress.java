package com.example.nicolas.clientefinalandroid2.Activities.Tabs.animaciones;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by nicolas on 01/08/15.
 */
public class ThreadProgress extends Thread
{
    private ProgressBar progressBar;
    public ThreadProgress(ProgressBar progressBar)
    {
        this.progressBar = progressBar;
    }
    public void run()
    {
        progressBar.setVisibility(View.VISIBLE);
    }
}
