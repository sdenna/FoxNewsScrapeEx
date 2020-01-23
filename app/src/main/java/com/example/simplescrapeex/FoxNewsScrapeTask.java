package com.example.simplescrapeex;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class FoxNewsScrapeTask extends AsyncTask<Void, Void, Void> {

    // Used this source to learn how to make an AsyncTask able to start an intent
    private final Activity mActivity;

    Document document;
    //ArrayList<String> myTitles;
    Elements tags, h2Tags;

    ArrayList<Story> myStories;

    public FoxNewsScrapeTask(final Activity mActivity) {
        MainActivity.myTitles.clear();
        this.mActivity = mActivity;
        myStories = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.foxnews.com/";
       // myTitles = new ArrayList<>();           // Arraylist for the titles of articles


        try {
            document = Jsoup.connect(url).validateTLSCertificates(false).get();

            /* Articles from the middle of the page */
            if (MainActivity.choice == 1) {
               tags = document.select(".collection.collection-article-list");
               h2Tags = tags.select("h2");

                MainActivity.myTitles.clear();  // delete old articles

                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    MainActivity.myTitles.add(s);

                    //For the next part - an ArrayList of Story objects to send to a ListView
                    // extract the href link that is inside the h2
                    String link = title.select("a[href]").attr("href");
                    Story story = new Story(s, link);
                    myStories.add(story);
                    // add it to the Story arraylist
                    //Log.d("Denna", link);
                }


            }

            // Second section - Fox News Flash

            else if (MainActivity.choice == 2) {
                tags = document.select(".collection.collection-must-read.js-must-read");
                h2Tags = tags.select("h2");

                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    MainActivity.myTitles.add(s);
                }
            }

            else {
                // Third section - Opinion articles

                tags = document.select(".collection.collection-opinion.has-load-more.js-opinion");
                h2Tags = tags.select("h2");

                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    MainActivity.myTitles.add(s);
                }
            }

        }
        catch (Exception e){
            Log.i("Denna", "Error: inside catch for doInBackground");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        String allTitles = "";
        for (String s: MainActivity.myTitles)
            allTitles += s + "\n\n";

        // Start Display intent to show Stories in a listview.  Waits to show them until the data has finished scraping
        Intent intent = new Intent(this.mActivity.getBaseContext(), DisplayArticleActivity.class);
        intent.putExtra("stories", MainActivity.myTitles);
        mActivity.startActivity(intent);
        Log.d("Denna", "All done!");
    }

    /*
    Intent intent = new Intent(MainActivity.this, DisplayArticleActivity.class);
        intent.putExtra("stories", myTitles);
        startActivity(intent);
     */



}
