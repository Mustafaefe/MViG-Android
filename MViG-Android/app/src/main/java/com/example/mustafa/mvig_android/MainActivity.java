package com.example.mustafa.mvig_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.LogRecord;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;
    Thread t1;
    int Kontrol = 0;
    //Treatment ProgressDialog
    public void loadingInIncrements(){

        progress = new ProgressDialog(this);
        progress.setMessage("Sending Message");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(false);
        progress.setProgress(100);
        progress.show();

        Log.i("Kontrol1",Kontrol+"");
        final int totalProgressTime = 100;
        t1 = new Thread() {
                @Override
                public void run() {
                    int jumpTime = 0;
                    while (jumpTime < totalProgressTime) {
                        try {
                            Thread.sleep(200);
                            jumpTime += 5;
                            progress.setProgress(jumpTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    progress.dismiss();
                }
            };
        t1.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        EditText receiver = (EditText) findViewById(R.id.edt_receiver);
        EditText message = (EditText) findViewById(R.id.edt_message);
        receiver.setText("213213");
        message.setText("Mesajjj");

        loadingInIncrements();
        getMessages();



    }

    public void getMessages(){
        try {
            Document doc = Jsoup.connect("http://mvig.azurewebsites.net/rss").get();
            Elements e = doc.select("body > div:nth-child(2) > div > div > div > table > tbody");
            Log.i("E: ",""+e.size());
            Elements elements1 = e.select("tbody:nth-child("+(e.size()+1)+") > tr > td:nth-child(2)");
            Elements elements2 = e.select("tbody:nth-child("+(e.size()+1)+") > tr > td:nth-child(3)");
            Log.i("E: ",""+elements1.text());
            sendMessage(elements1.text(), elements2.text());
            /*for(int i=0; i<e.size(); i++){
                Log.i("AA: ",e.get(i).text()+"");
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String number, String content){

        SmsManager manager = SmsManager.getDefault();
        String no = number;
        String cntnt = content;
        manager.sendTextMessage(no,null,cntnt,null,null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
