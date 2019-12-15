package com.example.hostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecScreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    TextView login_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(SecScreen.this, MainActivity.class));
        }

        login_path=findViewById(R.id.brok);
        login_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecScreen.this,SignupActivity.class));
                finish();
            }
        });
    }
}
