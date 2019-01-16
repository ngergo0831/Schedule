package com.example.ngergo.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Intent intent = new Intent(this,MainScreen.class);
        startActivity(intent);
    }
}
