package ch.bbw.modul335.myapplication;

import android.graphics.Bitmap;

public class Note {
    private String title;
    private String description;
    private Bitmap picture;
    private String date;
    private String time;

    public Note(String title, String description, Bitmap picture, String date, String time) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
