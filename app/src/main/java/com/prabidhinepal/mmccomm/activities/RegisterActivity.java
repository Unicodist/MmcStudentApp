package com.prabidhinepal.mmccomm.activities;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.prabidhinepal.mmccomm.R;
import com.prabidhinepal.mmccomm.dto.LoginModel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    
    Button register_button;
    TextInputEditText edit_name, edit_phone, edit_password, edit_confirm;
    AutoCompleteTextView edit_add;
    FirebaseAuth mAuth;
    List<TextInputEditText> toBeValidatedTE;
    ProgressDialog loading;
    String otpID;
    String serverAdd;
    String name, address, phone;
    TextView signup_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        setProgressDialog();
        registerSubmit();
        setSignUpToLoginListener();
        setEditTextProps();

        serverAdd = getAPIServerAddress();
        mAuth = FirebaseAuth.getInstance();
    }

    private void setEditTextProps() { }

    private void setProgressDialog() {
        loading = new ProgressDialog(this);
        loading.setTitle("Please wait");

        LoginModel lfvm;
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
        edit_name = findViewById(R.id.register_form_name);
        edit_add = findViewById(R.id.register_form_add);
        edit_phone = findViewById(R.id.register_form_phone);
        edit_password = findViewById(R.id.register_form_password);
        edit_confirm = findViewById(R.id.register_form_confirm);
        signup_to_login = findViewById(R.id.signup_to_login);
    }

    public void registerSubmit(){
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateFields()) return;

                name = edit_name.getText().toString();
                address = edit_add.getText().toString();
                phone = edit_phone.getText().toString();

                sendPhoneAuth(phone);
            }
        });
    }

    private boolean validateFields() {
        toBeValidatedTE = Arrays.asList(edit_phone,edit_name);
        for (EditText e :
                toBeValidatedTE) {
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