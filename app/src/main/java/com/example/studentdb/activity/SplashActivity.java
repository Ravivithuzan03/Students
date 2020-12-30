package com.example.studentdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentdb.R;

public class SplashActivity extends AppCompatActivity {

    TextView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//        getSupportActionBar().hide();
//        iv = findViewById(R.id.iv);
//
//        iv.animate().rotation(360f).setDuration(2000); //animate

        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        try{
                            Thread.sleep(2000);
                            startActivity(new Intent(SplashActivity.this,AddStudentActivity.class));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        t.start();
    }
}
