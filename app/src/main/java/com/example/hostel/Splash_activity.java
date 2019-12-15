package com.example.hostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        Thread timer = new Thread(){
            @Override
            public void run (){
                try {
                    sleep(2000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(Splash_activity.this,SecScreen.class));
                    finish();
                }
            }
        };

        timer.start();
    }
}
