package com.example.boris.worldexplorer.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.boris.worldexplorer.R;

/**
 * Created by boris on 8/9/17.
 */

public class CustomGridViewAdapter extends BaseAdapter {

    private Context mContext;
    final private String[] newsChannel;
   final private Integer[] thumbID;


    //private int channelNum = 0;

    public CustomGridViewAdapter(Context c, String[] news,Integer[] thumbID) {
        mContext = c;
        this.newsChannel = news;
        this.thumbID = thumbID;
    }


    public int getCount() {
            return thumbID.length;
    }


    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        if (convertView == null) {
            LayoutInflater inflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridViewAndroid = inflater.inflate(R.layout.single_grid,parent, false);
        } else {
            gridViewAndroid = (View) convertView;
            }

        TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
        ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
        imageViewAndroid.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imageViewAndroid.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewAndroid.setPadding(8, 8, 8, 8);
        textViewAndroid.setText(newsChannel[position]);
        imageViewAndroid.setImageResource(thumbID[position]);
        return gridViewAndroid;

    }
}
