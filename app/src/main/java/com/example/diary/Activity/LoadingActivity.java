package com.example.diary.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diary.R;

public class LoadingActivity extends AppCompatActivity {
    String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        try {
            Thread.sleep(1500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
