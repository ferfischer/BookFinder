package com.fernandofischer.bookfinder;

import android.graphics.Bitmap;

/**
 * Created by ferna on 16/07/2017.
 */

public class Book {

    private String title;
    private String subtitle;
    private String author;
    private String thumbnail;
    private Bitmap bitmap;

    public Book(String title, String subtitle, String author, String thumbnail, Bitmap bitmap) {
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.thumbnail = thumbnail;
        this.bitmap = bitmap;
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

    public Bitmap getBitmap() {
        return bitmap;
    }
}
