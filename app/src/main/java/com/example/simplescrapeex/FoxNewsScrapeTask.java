package com.example.simplescrapeex;
import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class FoxNewsScrapeTask extends AsyncTask<Void, Void, Void> {

    Document document;
    ArrayList<String> myTitles;
    Elements tags, h2Tags;

    ArrayList<Story> myStories = new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.foxnews.com/";
        myTitles = new ArrayList<>();           // Arraylist for the titles of articles

        try {
            document = Jsoup.connect(url).validateTLSCertificates(false).get();

            /* Articles from the middle of the page */
            if (MainActivity.choice == 1) {
               tags = document.select(".collection.collection-article-list");
               h2Tags = tags.select("h2");


                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    myTitles.add(s);

                    //For the next part - an ArrayList of Story objects to send to a ListView
                    // extract the href link that is inside the h2
                    String link = title.select("a[href]").attr("href");
                    Story story = new Story(s, link);
                    myStories.add(story);
                    // add it to the Story arraylist
                    Log.d("Denna", link);
                }


            }

            // Second section - Fox News Flash

            else if (MainActivity.choice == 2) {
                tags = document.select(".collection.collection-must-read.js-must-read");
                h2Tags = tags.select("h2");

                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    myTitles.add(s);
                }
            }

            else if (MainActivity.choice == 3){
                // Third section - Opinion articles

                tags = document.select(".collection.collection-opinion.has-load-more.js-opinion");
                h2Tags = tags.select("h2");

                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    myTitles.add(s);
                }
            }

            else
            {
                // chose option 4
                tags = document.select(".collection.collection-article-list");
                h2Tags = tags.select("h2");

                for (Element title : h2Tags) {
                    String s = title.select(".title").text();
                    myTitles.add(s);
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
        for (String s: myTitles)
            allTitles += s + "\n\n";

        MainActivity.textView.setText(allTitles);

    }

}
