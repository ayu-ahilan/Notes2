package ch.bbw.modul335.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

import ch.bbw.modul335.myapplication.R;
import ch.bbw.modul335.myapplication.model.Note;
import ch.bbw.modul335.myapplication.receiver.AlarmReceiver;

public class AddNote extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public int notificationId = 1;

    ImageView selectedImage;
    Bitmap bmap;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private Button saveButton;
    private EditText noteTitle;
    private EditText noteDescription;
    Bitmap imageBitmap;
    int hour, min;

    private String title;
    private String description;
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
        selectedImage = (ImageView)findViewById(R.id.preview);
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
            selectedImage = findViewById(R.id.preview);
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            selectedImage.setImageBitmap(imageBitmap);
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

    public void save(View view) throws ParseException, FileNotFoundException {
        title = noteTitle.getText().toString();
        description = noteDescription.getText().toString();
        bmap = imageBitmap;
        date = dateButton.getText().toString();
        time = timeButton.getText().toString();

        if (date != null && time != null) {
            Intent intent = new Intent(AddNote.this, AlarmReceiver.class);
            intent.putExtra("nofificationId", notificationId);
            intent.putExtra("textOfNotification", title);

            PendingIntent alarmIntent = PendingIntent.getBroadcast(AddNote.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY, hour);
            startTime.set(Calendar.MINUTE, min);
            startTime.set(Calendar.SECOND, 0);
            long alarmStartTime = startTime.getTimeInMillis();

            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

            Calendar cal = Calendar.getInstance();

            System.out.println(date);
            System.out.println(time);
            System.out.println();
            System.out.println(cal.get(Calendar.YEAR));
            System.out.println(cal.get(Calendar.MONTH));
            System.out.println(cal.get(Calendar.DAY_OF_MONTH));
            System.out.println(hour);
            System.out.println(min);

        }

        Note note = new Note(title, description, bmap, date, time);
        MainActivity.getInstance().noteList.add(note);

        /*//speichern
        String filename = "settings.json";
        Context context = this;

        File file = new File(context.getFilesDir(), filename);
        String[] files = context.fileList();

        String fileContents = title + "," + description + "," + bmap.toString() + "," + date + "," + time;
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        setContentView(R.layout.activity_main);
        saveButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        this.finish();
    }
}