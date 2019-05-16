package com.example.mynewsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FgNewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FgNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FgNewsFragment extends Fragment {
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_SPORT = 1;
    public static final int NEWS_TYPE_POLICY = 2;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentsTitles = new ArrayList<>();
    private TabLayout tlNews;
    private ViewPager vpNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fg_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tlNews = view.findViewById(R.id.tl_news);
        vpNews = view.findViewById(R.id.vp_news);
        setViewPager();
        vpNews.setOffscreenPageLimit(2);
        tlNews.setupWithViewPager(vpNews);
    }

    private void setViewPager() {
        mFragments.add(FgNewsListFragment.newInstance(NEWS_TYPE_TOP));
        mFragments.add(FgNewsListFragment.newInstance(NEWS_TYPE_SPORT));
        mFragments.add(FgNewsListFragment.newInstance(NEWS_TYPE_POLICY));

        mFragmentsTitles.add("头条");
        mFragmentsTitles.add("运动");
        mFragmentsTitles.add("政治");

        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments, mFragmentsTitles);
        vpNews.setAdapter(adapter);
    }
}
