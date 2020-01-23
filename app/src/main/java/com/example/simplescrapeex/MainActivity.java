package com.example.simplescrapeex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static TextView textView;
    static Button buttonFox;
    static Button buttonFoxNewsFlash;
    static Button buttonFoxOpinion;
    static int choice = 0;

    static ArrayList<String> myTitles;
    static boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTitles = new ArrayList<>();


        textView = (TextView)findViewById(R.id.textView);
        buttonFox = (Button)findViewById(R.id.buttonFoxMain);
        buttonFoxNewsFlash = (Button)findViewById(R.id.buttonFoxNewsFlash);
        buttonFoxOpinion = (Button)findViewById(R.id.buttonOpinionFox);
    }

    public void displayResults() {
        Intent intent = new Intent(MainActivity.this, DisplayArticleActivity.class);
        intent.putExtra("stories", myTitles);
        startActivity(intent);
    }

    public void scrapeFoxMain(View v) {
        choice = 1;
        final FoxNewsScrapeTask myScrape = new FoxNewsScrapeTask(this);
        myScrape.execute();

        // how can I make it wait????
       // displayResults();

    }

    public void scrapeFoxNewsFlash(View v) {
        choice = 2;
        final FoxNewsScrapeTask myScrape = new FoxNewsScrapeTask(this);
        myScrape.execute();
    }

    public void scrapeOpinionsFox(View v) {
        choice = 3;
        final FoxNewsScrapeTask myScrape = new FoxNewsScrapeTask(this);
        myScrape.execute();
    }

}


