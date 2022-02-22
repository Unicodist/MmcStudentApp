package com.prabidhinepal.mmccomm.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.prabidhinepal.mmccomm.R;
import com.prabidhinepal.mmccomm.activities.AuthFrontActivity;

public class ConfigureActivity extends AppCompatActivity {

    Intent registerIntent;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        initValues();

        registerIntent = new Intent(getApplicationContext(), AuthFrontActivity.class);
        startActivity(registerIntent);
    }
    private void initValues(){
        initViews();
        preferences = getSharedPreferences("DB_INFO",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("serveradd","192.168.1.64");
        editor.commit();
    }
    private void initViews(){
//        splashScreenView = findViewById(R.id.splash_screen_view);
//        splashScreenView.setActivated(true);
    }

}