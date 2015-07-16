package com.example.nicolas.testgradle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.nicolas.testgradle.activities.ActivityInsercionApuesta;


public class MainActivity extends ActionBarActivity
{
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //webView = (WebView) findViewById(R.id.webView);
        //webView.loadUrl("file:///res/mipmap/img1.gif");

        Thread welcomeThread = new Thread() {
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                    Intent i = new Intent(MainActivity.this, ActivityInsercionApuesta.class);
                    startActivity(i);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        welcomeThread.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
