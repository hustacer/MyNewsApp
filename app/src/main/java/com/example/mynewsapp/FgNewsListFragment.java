package com.example.mynewsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FgNewsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FgNewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FgNewsListFragment extends Fragment {
    private int mType;
    private TextView tvNews;

    public static FgNewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fg_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mType = getArguments().getInt("type");
        tvNews = view.findViewById(R.id.tv_news);
        switch (mType) {
            case FgNewsFragment.NEWS_TYPE_TOP:
                tvNews.setText("top");
                break;
            case FgNewsFragment.NEWS_TYPE_SPORT:
                tvNews.setText("sport");
                break;
            case FgNewsFragment.NEWS_TYPE_POLICY:
                tvNews.setText("policy");
                break;
        }
    }
}
