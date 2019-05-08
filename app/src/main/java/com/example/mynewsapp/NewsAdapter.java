package com.example.mynewsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//public class NewsAdapter extends BaseAdapter {
//    private List<NewsBean> mList;
//    private LayoutInflater mLayoutInflater;
//    private ImageLoader mImageLoader;
//
//    public NewsAdapter(Context context, List<NewsBean> data) {
//        this.mList = data;
//        this.mLayoutInflater = LayoutInflater.from(context);
//        mImageLoader = new ImageLoader();
//    }
//
//    @Override
//    public int getCount() {
//        return mList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = mLayoutInflater.inflate(R.layout.new_layout, null);
//            viewHolder.ivIcon = convertView.findViewById(R.id.newsIcon);
//            viewHolder.title = convertView.findViewById(R.id.newsTitle);
//            viewHolder.content = convertView.findViewById(R.id.newsContent);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
//        String url = mList.get(position).newIconUrl;
//        viewHolder.ivIcon.setTag(url);
//        // use thread
////        new ImageLoader().showImageByThead(viewHolder.ivIcon, url);
//        // use AsyncTask
//        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, url);
//        viewHolder.title.setText(mList.get(position).newTitle);
//        viewHolder.content.setText(mList.get(position).newContent);
//
//        return convertView;
//    }
//
//    class ViewHolder {
//        public TextView title,content;
//        public ImageView ivIcon;
//    }
//}

class MyNewsHolder extends RecyclerView.ViewHolder {
    public TextView title,content;
    public ImageView ivIcon;

    public MyNewsHolder(@NonNull View itemView) {
        super(itemView);
        ivIcon = (ImageView)itemView.findViewById(R.id.newsIcon);
        title = (TextView)itemView.findViewById(R.id.newsTitle);
        content = (TextView)itemView.findViewById(R.id.newsContent);
    }
}

public class NewsAdapter extends RecyclerView.Adapter<MyNewsHolder> {
    private List<NewsBean> mList;
    private LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;

    public NewsAdapter(Context context, List<NewsBean> data) {
        this.mList = data;
        this.mLayoutInflater = LayoutInflater.from(context);;
        this.mImageLoader = new ImageLoader();;
    }

    @NonNull
    @Override
    public MyNewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = mLayoutInflater.inflate(R.layout.new_layout, viewGroup, false);
        MyNewsHolder viewHolder = new MyNewsHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsHolder myNewsHolder, int position) {
        String url = mList.get(position).newIconUrl;
//        myNewsHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        myNewsHolder.ivIcon.setTag(url);
        mImageLoader.showImageByAsyncTask(myNewsHolder.ivIcon, url);

        myNewsHolder.title.setText(mList.get(position).newTitle);
        myNewsHolder.content.setText(mList.get(position).newContent);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
