package com.prabidhinepal.mmccomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.PhoneAuthCredential;

public class OtpPage extends AppCompatActivity {

    String otpID;
    PhoneAuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);

        otpID = getIntent().getStringExtra("otpid");

    }
}