package com.example.simplescrapeex;

public class Story {

    private String title;
    private String link;

    public Story(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
