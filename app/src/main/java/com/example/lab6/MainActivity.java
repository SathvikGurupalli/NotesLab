package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")){
            goToSecondScreen();
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void clickFunction(View view) {

        //get username
        Log.i("Info", "button pressed");
        EditText textField = (EditText) findViewById(R.id.username) ;
        String enteredUserName = textField.getText().toString();

        //add username to shared preferences:
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab6", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", enteredUserName).apply();

        //start second activity

        goToSecondScreen();




    }

    public void goToSecondScreen(){

       Intent intent = new Intent(this, MainActivity2.class);
       startActivity(intent);
    }
}