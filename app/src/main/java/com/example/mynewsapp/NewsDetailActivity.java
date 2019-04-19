package com.example.mynewsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity {
    TextView mTitleTextView;
    TextView mContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        mTitleTextView = (TextView)findViewById(R.id.news_detail_title);
        mContentTextView = (TextView)findViewById(R.id.news_detail_content);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("newsTitle");

        mTitleTextView.setText(title);
    }
}
