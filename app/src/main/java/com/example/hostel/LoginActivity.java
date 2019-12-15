package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText loginEmailET, loginPasswordET;
    Button loginSubmitBtn, goToSignupActBtn;
    ProgressBar loginSubmitPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();

        loginEmailET = findViewById(R.id.loginEmailET);
        loginPasswordET = findViewById(R.id.loginPasswordET);
        loginSubmitBtn = findViewById(R.id.loginSubmitBtn);
        loginSubmitPB = findViewById(R.id.loginSubmitPB);
        goToSignupActBtn = findViewById(R.id.goToSignupActBtn);

        loginSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSubmitBtn.setVisibility(View.GONE);
                loginSubmitPB.setVisibility(View.VISIBLE);

                String email = loginEmailET.getText().toString().trim();
                String pass = loginPasswordET.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    loginSubmitBtn.setVisibility(View.VISIBLE);
                                    loginSubmitPB.setVisibility(View.GONE);

                                    finish();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "User doesnot exists", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
