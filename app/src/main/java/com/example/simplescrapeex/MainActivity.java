package com.example.simplescrapeex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static TextView textView;
    static Button buttonFox;
    static Button buttonFoxNewsFlash;
    static Button buttonFoxOpinion;
    static int choice = -1;

    static boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textView);
        buttonFox = (Button)findViewById(R.id.buttonFoxMain);
        buttonFoxNewsFlash = (Button)findViewById(R.id.buttonFoxNewsFlash);
        buttonFoxOpinion = (Button)findViewById(R.id.buttonOpinionFox);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void scrapeFoxMain(View v) {
        choice = 0;
        final FoxNewsScrapeTask myScrape = new FoxNewsScrapeTask(this);
        myScrape.execute();
    }

    public void scrapeFoxNewsFlash(View v) {
        choice = 1;
        final FoxNewsScrapeTask myScrape = new FoxNewsScrapeTask(this);
        myScrape.execute();
    }

    public void scrapeOpinionsFox(View v) {
        choice = 2;
        final FoxNewsScrapeTask myScrape = new FoxNewsScrapeTask(this);
        myScrape.execute();
    }
}
