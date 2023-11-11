package com.example.diary.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.diary.Item.MemoArray;
import com.example.diary.Item.MemoItem;
import com.example.diary.R;
import com.example.diary.RecyclerViewAdapter;
import com.example.diary.RecyclerViewItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {
    private String TAG = this.getClass().getSimpleName();

    public static final int REQUEST_CODE_MENU = 101;
    private String strTitle;
    private String strContent;
    private String strWeather;
    private boolean Modified = false;

    private ArrayList<RecyclerViewItem> dataLists;
    private ArrayList<MemoItem> memoItems;

    private LinearLayout linearLayout;

    private MemoArray memoArray;

    private RecyclerView rv;//리사이클러뷰
    private LinearLayoutManager layoutManager;//레이아웃 메니저
    private RecyclerViewAdapter rvAdapter;//리사이클러뷰 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("일기장");

        memoArray = MemoArray.getInstance();
        memoItems = memoArray.getMemoItems();
        dataLists = new ArrayList<>();
        rv = findViewById(R.id.recyclerview);
        setRecyclerView();//리사이클러뷰 셋
        boolean modified;

        if(getIntent() != null) {
            Intent refIntent = this.getIntent();
            strTitle = refIntent.getStringExtra("title");
            strContent = refIntent.getStringExtra("content");
            strWeather = refIntent.getStringExtra("weather");
            modified = refIntent.getBooleanExtra("Modified",Modified);
        }

        linearLayout = findViewById(R.id.memolist_mainacitivity);

        DBActivity.MyDBHelper myHelper = DBActivity.MyDBHelper.getInstance(getApplicationContext());
        SQLiteDatabase sqlDB = myHelper.getReadableDatabase();

        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM "+"Diary"+";", null);

        while(cursor.moveToNext()) {
            RecyclerViewItem recyclerViewItem = new RecyclerViewItem();
            recyclerViewItem.setTitle(cursor.getString(0));
            recyclerViewItem.setContent(cursor.getString(1));
            recyclerViewItem.setWeather(cursor.getString(2));
            dataLists.add(recyclerViewItem);
        }
    }

    //리사이클러뷰 셋
    public void setRecyclerView() {
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        rvAdapter = new RecyclerViewAdapter(dataLists, this);
        rvAdapter.setOnClickListener(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(rvAdapter);
    }

    public void onWriteButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    //리사이클러뷰 아이템 클릭 시 호출되는 콜백 메소드
    @Override
    public void onTitleClicked(int position) {
        //ViewMain액티비티로 해당 제목과 같은 row에 저장된 db데이터 전달
        Intent intent = new Intent(MainActivity.this, ViewMainActivity.class);
        intent.putExtra("title", dataLists.get(position).getTitle());
        intent.putExtra("content", dataLists.get(position).getContent());
        intent.putExtra("weather", dataLists.get(position).getWeather());
        startActivity(intent);
    }
}