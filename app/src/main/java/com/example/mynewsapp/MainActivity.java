package com.example.mynewsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
//    private ListView mListView;
    private RecyclerView mListView;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private List<NewsBean> mList;

    private View mViewStatus;
    private ImageView mTitleNewsImageView;
    private ImageView mTitleMovieImageView;
    private ImageView mTitleXiaoHuaImageView;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initContentFragment();

//        mListView = (ListView) findViewById(R.id.lv_main);
//        mListView = (RecyclerView) findViewById(R.id.lv_main);
//        new NewsAsyncTask().execute(URL);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
//                intent.putExtra("newsTitle", mList.get(position).newTitle );
//                startActivity(intent);
//            }
//        });
//        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mListView.setLayoutManager(linerLayoutManager);
    }

    private void initView() {
        mViewStatus = (View)findViewById(R.id.view_status);
        mTitleNewsImageView = (ImageView)findViewById(R.id.iv_title_news);
        mTitleMovieImageView = (ImageView)findViewById(R.id.iv_title_movie);
        mTitleXiaoHuaImageView = (ImageView)findViewById(R.id.iv_title_xiaohua);
        mViewPager = (ViewPager)findViewById(R.id.vp_content);
        mToolbar = (Toolbar)findViewById(R.id.toolbars);

        mTitleNewsImageView.setOnClickListener(this);
        mTitleMovieImageView.setOnClickListener(this);
        mTitleXiaoHuaImageView.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_news:
                setCurrentItem(0);
                break;
            case R.id.iv_title_movie:
                setCurrentItem(1);
                break;
            case R.id.iv_title_xiaohua:
                setCurrentItem(2);
                break;
            default:
                break;
        }


    }

    private void initContentFragment() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new FgNewsFragment());
        fragmentArrayList.add(new FgMovieFragment());
        fragmentArrayList.add(new FgXiaohuaFragment());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentArrayList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(this);

//        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        setCurrentItem(0);
    }

    private void setCurrentItem(int i) {
        mViewPager.setCurrentItem(i);
        mTitleNewsImageView.setSelected(false);
        mTitleMovieImageView.setSelected(false);
        mTitleXiaoHuaImageView.setSelected(false);

        switch (i) {
            case 0:
                mTitleNewsImageView.setSelected(true);
                break;
            case 1:
                mTitleMovieImageView.setSelected(true);
                break;
            case 2:
                mTitleXiaoHuaImageView.setSelected(true);
                break;
            default:
                break;
        }
    }












    private List<NewsBean> getJsonData(String url) {
        List<NewsBean> newsBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            NewsBean newsBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i = 0;i < jsonArray.length(); i++ ){
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.newIconUrl = jsonObject.getString("picSmall");
                    newsBean.newTitle = jsonObject.getString("name");
                    newsBean.newContent = jsonObject.getString("description");
                    newsBeanList.add(newsBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsBeanList;
    }

    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }

            isr.close();
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {
        @Override
        protected List<NewsBean> doInBackground(String... params) {
            // get data from xml
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, newsBeans);
            mList = newsBeans;
            mListView.setAdapter(newsAdapter);
        }
    }
}
