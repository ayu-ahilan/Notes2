package ch.bbw.modul335.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNote extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView preview;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private Button saveButton;
    private EditText noteTitle;
    private EditText noteDescription;
    int hour, min;


    private String title;
    private String description;
    private Bitmap picture;
    private String date;
    private String time;


    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        timeButton = findViewById(R.id.timeButton);
        noteTitle = findViewById(R.id.NoteTitle);
        noteDescription = findViewById(R.id.NoteDescription);
        saveButton = findViewById(R.id.saveButton);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "JAN";
        if (month == 3)
            return "JAN";
        if (month == 4)
            return "JAN";
        if (month == 5)
            return "JAN";
        if (month == 6)
            return "JAN";
        if (month == 7)
            return "JAN";
        if (month == 8)
            return "JAN";
        if (month == 9)
            return "JAN";
        if (month == 10)
            return "JAN";
        if (month == 11)
            return "JAN";
        if (month == 12)
            return "JAN";
        // never happen
            return "JAN";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            preview = findViewById(R.id.preview);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            preview.setImageBitmap(imageBitmap);
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                min = selectedMinute;

                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, min));

            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, min, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void save(View view) {
        title = noteTitle.getText().toString();
        description = noteDescription.getText().toString();

        date = dateButton.getText().toString();
        time = timeButton.getText().toString();
        Note note = new Note(title, description, picture, date, time);
        MainActivity.getInstance().noteList.add(note);

        setContentView(R.layout.activity_main);
        saveButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        this.finish();
    }
}