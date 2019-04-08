package com.example.listviewsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EfficientAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private String[] mDataSource;
    private int[] mIcons;
    private int mResource;
    private Context mContext;

    public EfficientAdapter(Context context, int resource, String[] dataSource, int[] icons) {
        mContext = context;
        mResource = resource;
        mDataSource = dataSource;
        mIcons = icons;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataSource.length;
    }

    @Override
    public Object getItem(int position) {
        return mDataSource[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.textview);
            holder.imageView = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mDataSource[position]);
        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), mIcons[position]);
        holder.imageView.setImageBitmap(icon);
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
