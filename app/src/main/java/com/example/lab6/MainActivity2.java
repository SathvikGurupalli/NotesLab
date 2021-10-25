package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {


    TextView screen2Text;
    public static ArrayList<Note> notes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);


        screen2Text = (TextView) findViewById(R.id.screen2Text);
        Intent intent = getIntent();

        //use shared preferences to display welcome message
        String str2= sharedPreferences.getString("username","");
        screen2Text.setText("Welcome, " + str2);

        //sqlLite instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        //initiate notes variable
        DBHelper helper = new DBHelper(sqLiteDatabase);
        notes = helper.readNotes(str2);

        //create arraylist by iterating over notes
        ArrayList<String> displayNotes = new ArrayList<>();
        for(Note note: notes){
            displayNotes.add(String.format("Title:%s\nDate:%s",note.getTitle(),note.getDate()));
        }

        //Use ListView view to display notes on screen
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //on click listener for listview item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                goToFirstScreen();
                return true;
            case R.id.addNote:
                Intent intent = new Intent(this, MainActivity3.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToFirstScreen(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}