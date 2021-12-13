package ch.bbw.modul335.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UpdateNote extends AppCompatActivity {

    EditText noteTitle;
    private EditText noteDescription;
    private Button dateButton;
    private Button timeButton;
    ImageView selectedImage;
    Bitmap bitmap;
    Note note;

    private static UpdateNote instance;

    public static UpdateNote getInstance() {
        return instance;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        noteTitle = findViewById(R.id.NoteTitle);
        noteDescription = findViewById(R.id.NoteDescription);
        dateButton = findViewById(R.id.datePickerButton);
        timeButton = findViewById(R.id.timeButton);
        //selectedImage = selectedImage.getRootView().findViewById(R.id.preview);
        instance = this;
        Intent intent = getIntent();
        int id = (int) intent.getLongExtra("id", -1);
        if(id != -1) {
            Note note = MainActivity.getInstance().noteList.get(id);
            noteTitle.setText(note.getTitle());
            noteDescription.setText(note.getDescription());
            //selectedImage.setImageBitmap(note.getPicture());
            dateButton.setText(note.getDate());
            timeButton.setText(note.getTime());
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