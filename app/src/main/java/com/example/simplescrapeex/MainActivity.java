package com.example.simplescrapeex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // These variables are static so that the class that is doing the webscraping, WebscrapeTask.java
    // is able to directly reference them.  Some other sites I have seen use an inner class instead of
    // a separate class.  From what I can see, both ways work the same.

    static TextView textView;
    static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
    }

    public void scrape(View v) {
        Log.d("Denna", "Inside scrape method");

        // This will execute the asynchronos task of webscraping in the background.  If we didn't
        // have it this way, then the app wouldn't be able to do anything else while it was scraping
        // This class allows us to execute another task in the background.  This execute method
        // is essentially what is getting everything "going"
        new WebscrapeTask().execute();
    }

}
