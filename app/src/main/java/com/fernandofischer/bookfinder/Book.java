package com.fernandofischer.bookfinder;

/**
 * Created by ferna on 16/07/2017.
 */

public class Book {

    private String title;
    private String subtitle;
    private String author;
    private String thumbnail;

    public Book(String title, String subtitle, String author, String thumbnail) {
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
