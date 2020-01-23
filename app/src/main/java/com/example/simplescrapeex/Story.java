package com.example.simplescrapeex;

import android.os.Parcel;
import android.os.Parcelable;

public class Story implements Parcelable {

    private String title;
    private String link;

    // needed  for the Parcelable code to work
    public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>() {

        @Override
        public Story createFromParcel(Parcel parcel) {
            return new Story(parcel);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[0];
        }
    };

    public Story(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public Story(Parcel parcel) {
        title = parcel.readString();
        link = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
    }


    @Override
    public int describeContents() {
        return 0;
    }


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }


}
