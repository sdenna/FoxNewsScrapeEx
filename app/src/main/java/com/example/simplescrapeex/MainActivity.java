package com.example.simplescrapeex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    static TextView textView;
    static Button buttonFox;
    static Button buttonFoxNewsFlash;
    static Button buttonFoxOpinion;
    static int choice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        buttonFox = (Button)findViewById(R.id.buttonFoxMain);
        buttonFoxNewsFlash = (Button)findViewById(R.id.buttonFoxNewsFlash);
        buttonFoxOpinion = (Button)findViewById(R.id.buttonOpinionFox);
    }

    public void scrapeFoxMain(View v) {
        choice = 1;
        new FoxNewsScrapeTask().execute();
    }

    public void scrapeFoxNewsFlash(View v) {
        choice = 2;
        new FoxNewsScrapeTask().execute();
    }

    public void scrapeOpinionsFox(View v) {
        choice = 3;
        new FoxNewsScrapeTask().execute();
    }

    public void scrapeMiddleStoriesFox(View v) {
        choice = 4;
        new FoxNewsScrapeTask().execute();
    }
}


