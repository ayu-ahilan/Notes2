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

}
