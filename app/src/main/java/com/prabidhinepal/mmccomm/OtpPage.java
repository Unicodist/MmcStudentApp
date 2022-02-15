package com.prabidhinepal.mmccomm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OtpPage extends AppCompatActivity {

    Button verifyButton;
    EditText otp_cell_1,otp_cell_2,otp_cell_3,otp_cell_4,otp_cell_5,otp_cell_6;
    ArrayList<EditText> otpCells = new ArrayList(6);
    PhoneAuthCredential credential;
    FirebaseAuth mAuth;
    SharedPreferences userSPreferences;
    String otpID, phone_number_value;
    TextView phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        initViews();
        initValues();
        setupOtpCells(otpCells);
        verifyOtp();
    }

    private void verifyOtp() {
        verifyButton.setOnClickListener(v->{
            String userCode = otp_cell_1.getText().toString()+
                    otp_cell_2.getText().toString()+
                    otp_cell_3.getText().toString()+
                    otp_cell_4.getText().toString()+
                    otp_cell_5.getText().toString()+
                    otp_cell_6.getText().toString();

            credential = PhoneAuthProvider.getCredential(otpID,userCode);
            mAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }).
                    addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        });

    }

    private void setupOtpCells(@NonNull List<EditText> cells) {

        otpCells.add(otp_cell_1);
        otpCells.add(otp_cell_2);
        otpCells.add(otp_cell_3);
        otpCells.add(otp_cell_4);
        otpCells.add(otp_cell_5);
        otpCells.add(otp_cell_6);

        for (EditText cell:
             cells) {
            cell.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(i2!=0){
                        if (cells.indexOf(cell) < 5)
                            cells.get(cells.indexOf(cell)+1).requestFocus();
                        else{
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                            cell.clearFocus();
                        }

                    }
                }
                @Override
                public void afterTextChanged(Editable editable) {
                }
        });
            cell.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b){

                    }
                }
            });
        }

    }

    private void initValues() {
        userSPreferences = getSharedPreferences("USER_INFO",0);
        mAuth = FirebaseAuth.getInstance();
        otpID = getIntent().getStringExtra("otpid");
        phone_number_value = getIntent().getStringExtra("phone");
        phone_number.setText(phone_number_value);
    }

    private void initViews() {
        phone_number = findViewById(R.id.otp_page_phone_number);
        otp_cell_1 = findViewById(R.id.otp_cell_1);
        otp_cell_2 = findViewById(R.id.otp_cell_2);
        otp_cell_3 = findViewById(R.id.otp_cell_3);
        otp_cell_4 = findViewById(R.id.otp_cell_4);
        otp_cell_5 = findViewById(R.id.otp_cell_5);
        otp_cell_6 = findViewById(R.id.otp_cell_6);
        verifyButton = findViewById(R.id.otp_verify_button);
    }

}