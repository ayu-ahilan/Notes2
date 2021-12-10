package ch.bbw.modul335.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateNote extends AppCompatActivity {

    EditText noteTitle;
    Note note;

    /*public UpdateNote(Note note) {
        this.note = note;

    }*/

    private static UpdateNote instance;

    public static UpdateNote getInstance() {
        return instance;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        noteTitle = findViewById(R.id.NoteTitle);
        instance = this;
        Intent intent = getIntent();
        int id = (int) intent.getLongExtra("id", -1);
        if(id != -1) {
            Note note = MainActivity.getInstance().noteList.get(id);
            noteTitle.setText(note.getTitle());
        }
    }

    public void openDatePicker(View view) {
    }

    public void popTimePicker(View view) {
    }

    public void save(View view) {
    }

    public void setNote(Note note) {
        this.note = note;
    }


}