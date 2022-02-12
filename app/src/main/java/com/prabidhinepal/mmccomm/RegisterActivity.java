package com.prabidhinepal.mmccomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    
    Button register_button;
    EditText edit_fname, edit_lname, edit_add, edit_phone, edit_pw, edit_conf;
    FirebaseAuth mAuth;
    String otpID;
    String serverAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        serverAdd = getAPIServerAddress();
        mAuth = FirebaseAuth.getInstance();
        initViews();
        register_submit();
        
    }

    private String getAPIServerAddress() {
        SharedPreferences preferences = getSharedPreferences("DB_INFO",MODE_PRIVATE);
        serverAdd = preferences.getString("serveradd","127.0.0.1");
        Toast.makeText(this, serverAdd, Toast.LENGTH_SHORT).show();
        return serverAdd;
    }

    public void initViews(){
        register_button = findViewById(R.id.register_submit);
        edit_fname = findViewById(R.id.register_form_first_name);
        edit_lname = findViewById(R.id.register_form_last_name);
        edit_add = findViewById(R.id.register_form_address);
        edit_phone = findViewById(R.id.register_form_phone);
        edit_pw = findViewById(R.id.registerr_form_password);
        edit_conf = findViewById(R.id.register_form_confirm);
        
    }
    public void register_submit(){
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<EditText> toBeValidated = Arrays.asList(edit_add,edit_conf,edit_pw,edit_phone,edit_lname,edit_fname);

                if(!validateFields(toBeValidated)) return;

                String first_name, last_name, address, phone, password, confirm;

                first_name = edit_fname.getText().toString();
                last_name = edit_lname.getText().toString();
                address = edit_add.getText().toString();
                phone = edit_phone.getText().toString();
                password = edit_pw.getText().toString();
                confirm = edit_conf.getText().toString();

                sendPhoneAuth(phone);
            }
        });
    }

    private boolean validateFields(List<EditText> toBeValidated) {
        for (EditText e :
                toBeValidated) {
            if(TextUtils.isEmpty(e.getText())) return false;
        }
        return true;
    }

    public void sendPhoneAuth(String phone){

        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                registerUser();
                Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                otpID = s;
                Intent otpIntent = new Intent(RegisterActivity.this,OtpPage.class);
                otpIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                otpIntent.putExtra("otpid",otpID);
                startActivity(otpIntent);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder().
                setActivity(this).
                setPhoneNumber("+977"+phone).
                setCallbacks(mCallBacks).
                setTimeout(60L, TimeUnit.SECONDS).
                build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

//    private void registerUser() {
//        JsonObjectRequest request =
//    }
}