package ch.bbw.modul335.myapplication.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Note {
    private String title;
    private String description;
    private ImageView picture;
    private String date;
    private String time;
    private Bitmap bmap;

    public Note(String title, String description, Bitmap bmap, String date, String time) {
        this.title = title;
        this.description = description;
        this.bmap = bmap;
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
        return bmap;
    }

    public void setPicture(Bitmap bmap) {
        this.bmap = bmap;
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
