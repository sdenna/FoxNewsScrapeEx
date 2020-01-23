package com.example.simplescrapeex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayArticleActivity extends AppCompatActivity {

    // will change to be of type Story after get this working
    private ArrayList<String> myStories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_article);

        Intent intent = getIntent();
        // will change to be of type Story after get this working
        myStories = intent.getStringArrayListExtra("stories");
        // Get a reference to the ListView element to display all stories from the webscrape
        ListView allStoriesListView = (ListView) findViewById(R.id.storyList);
        // CustomAdapter is an inner class defined below that details how to adapt this arraylist of data
        CustomAdapter customAdapter = new CustomAdapter();
        allStoriesListView.setAdapter(customAdapter);

        // Referenced for syntax: https://www.youtube.com/watch?v=XyxT8IQoZkc
        // Create a setOnItemClickListener for the listView to find out which element they clicked on

        allStoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // will update this to be of type Story
                String story = myStories.get(i);

//                // start an intent to load the page to edit this element that has been clicked on
//                Intent intent = new Intent(DisplayEventsActivity.this, EditEventActivity.class);
//                intent.putExtra("event", event);
//                startActivity(intent);
            }
        });
    }



    public void backToHome(View v) {
        Intent intent = new Intent(DisplayArticleActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**Referred to this video regarding CustomAdapter and creating the custom class:
     * https://www.youtube.com/watch?v=FKUlw7mFXRM -->
     **/

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myStories.size();       // amount of elements in database
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         *Documentation:
         * https://developer.android.com/reference/android/widget/Adapter.html#getView(int,%20android.view.View,%20android.view.ViewGroup)
         *
         *
         * Get a View that displays the data at the specified position in the data set.
         * You can either create a View manually or inflate it from an XML layout file.
         * When the View is inflated, the parent View (GridView, ListView...) will apply
         * default layout parameters unless you use LayoutInflater.inflate(int, android.view.ViewGroup,
         * boolean) to specify a root view and to prevent attachment to the root.
         *
         * @param i         The element in the array you are displaying
         * @param view      The old view to reuse if possible.
         * @param viewGroup The parent group that this view will eventually be attached to
         * @return          The correct view laid out according to the xml file with the
         *                  data from the current entry i
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // attaches the custom xml design to this View you are creating
            view = getLayoutInflater().inflate(R.layout.row_data, null);

            // creates references to each element in the custom xml design.  That is why you
            // need to say view.findViewById because you have to reference the element that
            // was gotten from the LayoutInflater above

            TextView storyTitle = (TextView) view.findViewById(R.id.storyTitle);


            // Here I am getting the specific Story in the ArrayList we are currently displaying
            String s = myStories.get(i);

            // Eventually I will adjust this to pull out the title and the link

            // Set the correct story title and set the link to be active for what we are
            // displaying in the list
            storyTitle.setText(s);


            // return this view element with the correct data inserted
            return view;
        }


    }

}
