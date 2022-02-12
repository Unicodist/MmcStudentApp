package com.prabidhinepal.mmccomm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class AuthFrontActivity extends AppCompatActivity {

    Button auth_front_login_button;
    Button auth_front_signup_button;
    Intent gotoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_front);

        initViews();
        setButtonEvents();
    }

    private void setButtonEvents() {
        auth_front_login_button.setOnClickListener(v->{
            gotoIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(gotoIntent);
        });
        auth_front_signup_button.setOnClickListener(v->{
            gotoIntent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(gotoIntent);
        });
    }

    private void initViews() {
        auth_front_login_button = findViewById(R.id.auth_front_login_button);
        auth_front_signup_button = findViewById(R.id.auth_front_signup_button);
    }

}