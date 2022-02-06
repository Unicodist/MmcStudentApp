package com.prabidhinepal.mmccomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.home_navigation_view);
        if (getUserRole().equals("admin")){
            navigationView.inflateMenu(R.menu.sidebar_menu_admin);
        }
        else {
            navigationView.inflateMenu(R.menu.sidebar_menu_user);
        }
    }
    protected String getUserRole(){
        return "admin";
    }
}