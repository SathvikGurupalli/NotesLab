package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    int noteid=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText editText = (EditText) findViewById(R.id.notes);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteID", -1);

        if(noteid!=-1){
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }

    }

    public void saveFunction(View view) {

        //1
        EditText editText = (EditText) findViewById(R.id.notes);
        String content = editText.getText().toString();
        //2
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        //3
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        //4
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        //5
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_"+(noteid+1);
            dbHelper.saveNotes(username, title, content, date);
        }
        else
        {
            title = "NOTE_"+(noteid+1);
            dbHelper.updateNotes(username, title, content, date);
        }

        //6
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);


    }
}