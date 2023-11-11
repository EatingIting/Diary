package com.example.diary.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diary.Item.MemoArray;
import com.example.diary.Item.MemoItem;
import com.example.diary.R;

public class WriteActivity extends AppCompatActivity {
    String TAG = this.getClass().getSimpleName();

    private static int PICK_IMAGE_REQUEST = 1;
    private boolean Modified = false;

    EditText m_refEditTextTitle;
    EditText m_refEditTextContent;
    RadioGroup m_refRadioGroup;

    String strTitle = null;
    String strContent = null;
    String strWeather = null;
    RadioButton selectedRadioBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("일기쓰기");

        m_refEditTextTitle = (EditText) findViewById(R.id.editText4);
        m_refEditTextContent = (EditText) findViewById(R.id.editText22);
        m_refRadioGroup = (RadioGroup) findViewById(R.id.weather_radiogroup_writeactivity);

        Intent intent = getIntent();
        Modified = intent.getBooleanExtra("Modified", false);
        if (Modified) { // 수정눌러서 들어왔을 때 하는 일
            m_refEditTextTitle.setText(intent.getStringExtra("title"));
            m_refEditTextContent.setText(intent.getStringExtra("content"));
        }
        m_refRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioBTN = (RadioButton) findViewById(m_refRadioGroup.getCheckedRadioButtonId());
                strWeather = selectedRadioBTN.getText().toString();
                Toast.makeText(WriteActivity.this, "선택된 날씨는 " + strWeather + "입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void atpd_sendClick(View v) {
        strTitle = m_refEditTextTitle.getText().toString().trim();
        strContent = m_refEditTextContent.getText().toString().trim();

        DBActivity.MyDBHelper myHelper = DBActivity.MyDBHelper.getInstance(getApplicationContext());
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        if (Modified) {
            String query = "UPDATE Diary set title = '"+ m_refEditTextTitle.getText().toString() + "', contents = '"+m_refEditTextContent.getText().toString() +"', weather = '"+selectedRadioBTN.getText().toString()+"' where title = '"+ViewMainActivity.title+"' and contents = '"+ViewMainActivity.content+"';";
            sqlDB.execSQL(query);
            sqlDB.close();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            sqlDB.execSQL("INSERT INTO Diary "
                    + " Values ('" + strTitle + "', '" + strContent+"', '" + strWeather + "');");
            sqlDB.close();

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if (strTitle.length() > 0 && strContent.length() > 0) {
            Intent ref_Intent;

            ref_Intent = new Intent(this, MainActivity.class);
            ref_Intent.putExtra("title", strTitle);
            ref_Intent.putExtra("content", strContent);
            ref_Intent.putExtra("weather", strWeather);
            ref_Intent.putExtra("modified", Modified);
            MemoArray.getInstance().addMemoItem(new MemoItem(strTitle, strContent, strWeather));
            startActivity(ref_Intent);
            finish();
        }
    }
}