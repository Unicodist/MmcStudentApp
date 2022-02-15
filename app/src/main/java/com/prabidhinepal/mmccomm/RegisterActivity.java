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
import android.widget.TextView;
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
    EditText edit_fname, edit_lname, edit_add, edit_phone;
    FirebaseAuth mAuth;
    List<EditText> toBeValidated;
    ProgressDialog loading;
    String otpID;
    String serverAdd;
    String first_name, last_name, address, phone;
    TextView signup_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setProgressDialog();
        serverAdd = getAPIServerAddress();
        mAuth = FirebaseAuth.getInstance();
        initViews();
        registerSubmit();
        setSignUpToLoginListener();
        setEditTextProps();
        
    }

    private void setEditTextProps() {
    }

    private void setProgressDialog() {
        loading = new ProgressDialog(this);
        loading.setTitle("Please wait");
    }

    private void setSignUpToLoginListener() {
        signup_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(loginIntent);
//                finish();
            }
        });
    }

    private String getAPIServerAddress() {
        SharedPreferences preferences = getSharedPreferences("DB_INFO",MODE_PRIVATE);
        serverAdd = preferences.getString("serveradd","127.0.0.1");
        return serverAdd;
    }

    public void initViews(){
        register_button = findViewById(R.id.register_submit);
        edit_fname = findViewById(R.id.register_form_first_name);
        edit_lname = findViewById(R.id.register_form_last_name);
        edit_add = findViewById(R.id.register_form_add);
        edit_phone = findViewById(R.id.register_form_phone);
        signup_to_login = findViewById(R.id.signup_to_login);
        
    }

    public void registerSubmit(){
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toBeValidated = Arrays.asList(edit_add,edit_phone,edit_lname,edit_fname);

                if(!validateFields()) return;

                first_name = edit_fname.getText().toString();
                last_name = edit_lname.getText().toString();
                address = edit_add.getText().toString();
                phone = edit_phone.getText().toString();

                sendPhoneAuth(phone);
            }
        });
    }

    private boolean validateFields() {
        for (EditText e :
                toBeValidated) {
            if(TextUtils.isEmpty(e.getText())) return false;
        }
        return true;
    }

    public void sendPhoneAuth(String phone){

        loading.setMessage("We are sending a code to your device");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                loading.dismiss();

                otpID = s;
                Intent otpIntent = new Intent(RegisterActivity.this,OtpPage.class);
                otpIntent.putExtra("otpid",otpID);
                otpIntent.putExtra("phone",phone);
                startActivity(otpIntent);
                finish();
            }
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
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

}