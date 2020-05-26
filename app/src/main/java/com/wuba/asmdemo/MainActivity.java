package com.wuba.asmdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mTv.setText("");
        setText();
    }

    private void setText() {

    }

    private void initView() {
        mTv = findViewById(R.id.tv_text);
        SystemClock.sleep(1000);
    }
}
