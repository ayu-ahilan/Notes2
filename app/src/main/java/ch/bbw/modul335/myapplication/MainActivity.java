package ch.bbw.modul335.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNote = findViewById(R.id.addButton);

        addNote.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddNote.class);
            startActivity(intent);
        });
    }
}