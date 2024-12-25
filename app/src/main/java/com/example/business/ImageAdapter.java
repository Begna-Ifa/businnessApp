package com.example.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mImageResources;

    public ImageAdapter(Context context, List<Integer> imageResources) {
        mContext = context;
        mImageResources = imageResources;
    }

    @Override
    public int getCount() {
        return mImageResources.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageResources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.my_business, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(mImageResources.get(position));

        return convertView;
    }
}
