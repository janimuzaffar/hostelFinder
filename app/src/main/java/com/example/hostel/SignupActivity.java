package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    EditText signupUsernameET, signupEmailET, signupPasswordET;
    Button signupSubmitBtn, goToLoginActBtn;
    ProgressBar signupSubmitPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Signup");

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        signupEmailET = findViewById(R.id.signupEmailET);
        signupUsernameET = findViewById(R.id.signupUsernameET);
        signupPasswordET = findViewById(R.id.signupPasswordET);
        signupSubmitBtn = findViewById(R.id.signupSubmitBtn);
        goToLoginActBtn = findViewById(R.id.goToLoginActBtn);
        signupSubmitPB = findViewById(R.id.signupSubmitPB);


        signupSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupSubmitBtn.setVisibility(View.GONE);
                signupSubmitPB.setVisibility(View.VISIBLE);

                final String email = signupEmailET.getText().toString().trim();
                final String username = signupUsernameET.getText().toString().trim();
                final String pass = signupPasswordET.getText().toString().trim();

//                if (email == null || email.isEmpty()) {
////                    Toast.
//                    return;
//                }

                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("SIGNUP", "inside signup auth");
                                if (task.isSuccessful() && task.getResult() != null) {
                                    Log.d("SIGNUP", "inside signup auth SUCCESS");
                                    Users user = new Users(username, email, pass, null, new ArrayList<String>());

                                    firestore.collection(TableNames.USERS).document(task.getResult().getUser().getUid())
                                            .set(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Log.d("SIGNUP", "inside firestore ");
                                                    if (task.isSuccessful()) {
                                                        signupSubmitPB.setVisibility(View.GONE);
                                                        signupSubmitBtn.setVisibility(View.VISIBLE);

                                                        finish();
                                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                                    }
                                                }
                                            });
                                }
                                else {
                                    signupSubmitPB.setVisibility(View.GONE);
                                    signupSubmitBtn.setVisibility(View.VISIBLE);
                                    Log.d("SIGNUP", "error : " + task.getException());
                                }
                            }
                        });
            }
        });


        goToLoginActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }
}
