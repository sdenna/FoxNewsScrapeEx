package com.example.simplescrapeex;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WebscrapeTask extends AsyncTask<Void, Void, Void> {

    Document document;
    ArrayList<String> myTitles;

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.foxnews.com/";
        myTitles = new ArrayList<>();

        try {
            // Note this line below doesn't work in Jsoup 1.12.1.  Not sure why
            // It does work in jsoup 1.11.1 and earlier

            document = Jsoup.connect(url).validateTLSCertificates(false).get();

            /* Element is an object that can be iterated through.  So we do a query on the document
            that was gotten in the above line of code.  This line of code literally got the entire HTML
            code.  From here, we can query it or "scrape" for what we want.  In this code below we are
            selecting all elements (HTML tags) that have the attribute "content article-list".  In Jsoup
            you need to put a . before the attributes and you need to put a . in place of any spaces you may see
            when you inspect the website.

            To see what I mean, go to www.foxnews.com and then press Ctrl Shift I to inspect the page.  When you
            are in the code, press Ctrl F to search and type in content article-list.  You will see it highlight
            on the website all the areas that will be selected with this query.

            This code is getting all those elements (HTML tags), with the attribute content article-list,
            and then iterating among all the children - or subelements in that selection.  Each one of them
            is then further queried by selecting the first element of title, and getting its text.  The text is
            always the black text that is in between the HTML tags.  Finally, this text is added to our Strings
            arraylist.

             */

            for (Element stories: document.selectFirst(".content.article-list").children()) {
                String title = stories.selectFirst(".title").text();
                myTitles.add(title);
            }
            Log.i("Denna", "myTitles size is: "+ myTitles.size());
        }
        catch (Exception e){
            Log.i("Denna", "Error: inside catch for doInBackground");
        }

        return null;
    }

    /**
     * This method is called after the doInBackground method concludes its work.  In this scenario I wanted
     * to have the text of all the titles of the articles to be displaed on the app.  So I referened the static
     * variable for the TextView on MainActivity and using a for each loop got all the text, set the text, and
     * after this the text on the screen will be displayed with the scraped info.
     *
     * @param aVoid
     */

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.e("Denna", document.toString());
        String allTitles = "";
        for (String s: myTitles)
            allTitles += s + "\n\n";

        MainActivity.textView.setText(allTitles);
    }

}
