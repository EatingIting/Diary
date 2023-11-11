package com.example.diary.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diary.Item.MemoArray;
import com.example.diary.R;

public class ViewMainActivity extends AppCompatActivity {
    String TAG = this.getClass().getSimpleName();

    public static final int REQUEST_CODE_MENU = 101;

    TextView titleTV;
    TextView contentTV;
    TextView weatherTV;
    TextView currentTV;
    MemoArray memoArray = MemoArray.getInstance();


    private boolean Modified = false;

    public static String title;
    public static String content;
    String weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
        setTitle("일기보기");

        titleTV = findViewById(R.id.title_veiwmainacitivity);
        contentTV = findViewById(R.id.content_viewmainactivity);
        weatherTV = findViewById(R.id.weather_viewmainactivity);
//        currentTV = findViewById(R.id.time_viewmainactivity);

        if(getIntent()!=null) {
            Intent refIntent = this.getIntent();

            title = refIntent. getStringExtra("title");
            content = refIntent.getStringExtra("content");
            weather = refIntent.getStringExtra("weather");
            Modified = refIntent.getBooleanExtra("Modified",Modified);

            titleTV.setText("제목 : " + title);
            contentTV.setText("내용 : " + content);
            weatherTV.setText("날씨 : " + weather);
        }
    }

    public void onButton2Clicked(View v){
        Intent refIntent = new Intent(getApplicationContext(), MainActivity.class);
        refIntent.putExtra("title", title);
        refIntent.putExtra("content", content);
        refIntent.putExtra("weather",weather);
        refIntent.putExtra("Modified",Modified);
        startActivityForResult(refIntent, REQUEST_CODE_MENU);
    }
    public void onButton3Clicked(View v) {
        DBActivity.MyDBHelper myHelper = DBActivity.MyDBHelper.getInstance(getApplicationContext());
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();

        sqlDB.execSQL("DELETE FROM Diary WHERE title ='"+title + "'and contents ='"+content+"'and weather ='"+weather+"';");
        sqlDB.close();
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    public void onButton1Clicked(View v) {
        Intent modintent = new Intent(getApplicationContext(),WriteActivity.class);
        Modified = true;
        modintent.putExtra("Modified", Modified);
        modintent.putExtra("title", title);
        modintent.putExtra("content", content);
        startActivity(modintent);
    }
}
