package ch.bbw.modul335.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import ch.bbw.modul335.myapplication.R;
import ch.bbw.modul335.myapplication.model.Note;
import ch.bbw.modul335.myapplication.receiver.AlarmReceiver;

public class UpdateNote extends AppCompatActivity {

    EditText noteTitle;
    private EditText noteDescription;
    private Button dateButton;
    private Button timeButton;
    private Button saveButton;
    private DatePickerDialog datePickerDialog;
    private Button deleteButtton;
    private ImageView imageView;

    private String title;
    private String description;
    private String date;
    private String time;
    private Note generalNote;

    private int notificationId = 236;

    ImageView selectedImage;
    Note note;
    int hour, min;
    int id;

    private static UpdateNote instance;

    public static UpdateNote getInstance() {
        return instance;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        noteTitle = findViewById(R.id.NoteTitleup);
        noteDescription = findViewById(R.id.NoteDescriptionup);
        dateButton = findViewById(R.id.datePickerButtonup);
        timeButton = findViewById(R.id.timeButtonup);
        deleteButtton = findViewById(R.id.deleteButtonup);
        saveButton = findViewById(R.id.saveButtonup);
        imageView = findViewById(R.id.previewup);
        instance = this;
        Intent intent = getIntent();
        id = (int) intent.getLongExtra("id", -1);
        if (id != -1) {
            Note note = MainActivity.getInstance().noteList.get(id);
            noteTitle.setText(note.getTitle());
            noteDescription.setText(note.getDescription());
            imageView.setImageBitmap(note.getPicture());
            dateButton.setText(note.getDate());
            timeButton.setText(note.getTime());
            generalNote = note;
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
    }

    public void setNote (Note note){
        this.note = note;
    }

    public void save(View view) {
        /*title = noteTitle.getText().toString();
        description = noteDescription.getText().toString();
        selectedImage.buildDrawingCache();
        bmap = selectedImage.getDrawingCache();
        date = dateButton.getText().toString();
        time = timeButton.getText().toString();*/

        if (date != null && time != null) {
            Intent intent = new Intent(UpdateNote.this, AlarmReceiver.class);
            intent.putExtra("nofificationId", notificationId);
            intent.putExtra("textOfNotification", title);


            PendingIntent alarmIntent = PendingIntent.getBroadcast(UpdateNote.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY, hour);
            startTime.set(Calendar.MINUTE, min);
            startTime.set(Calendar.SECOND, 0);
            long alarmStartTime = startTime.getTimeInMillis();

            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
        }

        //Note note = new Note(title, description, bmap, date, time);
        note.setTitle(title);
        note.setDescription(description);
        //note.setBmap(title);
        note.setDate(date);
        note.setTime(time);

        MainActivity.getInstance().noteList.add(note);

        setContentView(R.layout.activity_main);
        saveButton.setOnClickListener(v -> {
            this.finish();
        });

    }

    public void delete (View view){
        if (id != -1) {
            MainActivity.getInstance().noteList.remove(generalNote);
        }
        this.finish();
    }
}