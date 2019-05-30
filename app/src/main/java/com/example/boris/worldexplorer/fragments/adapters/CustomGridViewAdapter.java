package com.example.boris.worldexplorer.fragments.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.worldexplorer.R;

/**
 * Created by boris on 8/9/17.
 */

public class CustomGridViewAdapter extends BaseAdapter {

    private Context mContext;
    final private String[] newsChannel;
    final private String[] channelIDS;
    private Integer[] wishListStatus;
   final private Integer[] thumbID;
    private static Integer[] favButton;

    private static LayoutInflater inflater = null;


    private int channelNum;

    public CustomGridViewAdapter(Context c, String[] news,Integer[] thumbID,String[] channelIDS,Integer[] wishListStatus) {
        mContext = c;
        this.newsChannel = news;
        this.thumbID = thumbID;
        this.channelIDS = channelIDS;
        this.wishListStatus = wishListStatus;
        inflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View gridViewAndroid;
        /*
        if (convertView == null) {
            LayoutInflater inflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridViewAndroid = inflater.inflate(R.layout.single_grid,parent, false);
        } else {
            gridViewAndroid = (View) convertView;
            }
            */
        gridViewAndroid = inflater.inflate(R.layout.single_grid,parent, false);
        TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
        ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
        //final Button favIcon = (Button) gridViewAndroid.findViewById(R.id.favIcon);
        //imageViewAndroid.setLayoutParams(new LinearLayout.LayoutParams(300, 300));

        imageViewAndroid.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewAndroid.setPadding(10, 10, 10, 10);
        imageViewAndroid.setElevation(50);
        textViewAndroid.setText(newsChannel[position]);
        imageViewAndroid.setImageResource(thumbID[position]);
//        if(wishListStatus[position] == 1)
//            favIcon.setBackground(gridViewAndroid.getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
//
//        favIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(wishListStatus[position] ==  0) {
//                    System.out.println("Button Clicked:Adding" + newsChannel[position] + "to favorites");
//                    favIcon.setBackground(gridViewAndroid.getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
//                    wishListStatus[position]=1;
//                }
//                else {
//                    favIcon.setBackground(gridViewAndroid.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
//                    wishListStatus[position] = 0;
//                }
//            }
//        });

        return gridViewAndroid;

    }
}
