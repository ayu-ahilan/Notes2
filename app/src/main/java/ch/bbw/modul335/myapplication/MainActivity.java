package ch.bbw.modul335.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addNote;
    //Objekte drin
    ArrayList<Note> noteList = new ArrayList<Note>();
    // Titel drin
    ArrayList<String> notesLists = new ArrayList<String>();
    ListView list;
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNote = findViewById(R.id.addButton);
        list = findViewById(R.id.listview);
        instance = this;
        addNote.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddNote.class);
            startActivity(intent);
        });
    }

    public void loadToDos(){
        notesLists.clear();
        for (Note note:noteList) {
            notesLists.add(note.getTitle());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadToDos();
        if (noteList.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, notesLists);
            list.setAdapter(arrayAdapter);
            /*list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
        }
    }
}