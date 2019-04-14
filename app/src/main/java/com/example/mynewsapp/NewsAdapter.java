package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> mList;
    private LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;

    public NewsAdapter(Context context, List<NewsBean> data) {
        this.mList = data;
        this.mLayoutInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.new_layout, null);
            viewHolder.ivIcon = convertView.findViewById(R.id.newsIcon);
            viewHolder.title = convertView.findViewById(R.id.newsTitle);
            viewHolder.content = convertView.findViewById(R.id.newsContent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        String url = mList.get(position).newIconUrl;
        viewHolder.ivIcon.setTag(url);
        // use thread
//        new ImageLoader().showImageByThead(viewHolder.ivIcon, url);
        // use AsyncTask
        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, url);
        viewHolder.title.setText(mList.get(position).newTitle);
        viewHolder.content.setText(mList.get(position).newContent);

        return convertView;
    }

    class ViewHolder {
        public TextView title,content;
        public ImageView ivIcon;
    }
}
