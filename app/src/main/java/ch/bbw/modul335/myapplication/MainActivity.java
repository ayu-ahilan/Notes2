package ch.bbw.modul335.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), UpdateNote.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        //Note skrr = readData();
        //System.out.println(skrr);
    }

    private Activity getActivity() {
        return this;
    }

    public void loadNotes() {
        notesLists.clear();
            for (Note note : noteList) {
                notesLists.add(note.getTitle());
            }
    }

    /*public Note readData() {
        String filename = "settings.json";
        Context context = this;

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
        } finally {
            String x = stringBuilder.toString();
            String[] values = x.split(",");

            String base64String = values[2];
            String base64Image = base64String.split(",")[1];

            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            Note note = new Note(
                    (values[0]),
                    (values[1]),
                    (decodedByte),
                    (values[3]),
                    (values[4])
            );
            return note;
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
        if (noteList.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, notesLists);
            list.setAdapter(arrayAdapter);
        }
    }
}