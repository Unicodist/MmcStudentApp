package com.prabidhinepal.mmccomm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigureActivity extends AppCompatActivity {

    Intent registerIntent;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        preferences = getSharedPreferences("DB_INFO",0);
        registerIntent = new Intent(getApplicationContext(),AuthFrontActivity.class);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("serveradd","192.168.69.69");
        editor.commit();

        startActivity(registerIntent);
    }

}