package com.example.boris.worldexplorer.fragments.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boris.worldexplorer.R;
import com.example.boris.worldexplorer.fragments.ReadMoreFragment;
import com.example.boris.worldexplorer.model.transaction.Article;
import com.example.boris.worldexplorer.model.transaction.DateFormatter;
import com.squareup.picasso.Picasso;

import java.util.Date;


/**
 * Created by boris on 8/13/17.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static final String TAG = "CustomAdapter";
    private Context context;
    private Article article;
    private DateFormatter df;
    private static TextView textView;
    private static TextView textView2;
    private static ImageView imageView;
    private static TextView textView3;
    private static TextView textView4;
    private String sourceURL;
    public CustomAdapter() {}

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */

    public CustomAdapter(Article article, Context context) {
        this.context = context;
        this.article = article;
        setHasStableIds(true);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public static CardView cv;

        public ViewHolder(View v, final Article art) {
            super(v);
            // Define click listener for the ViewHolder's View.
            cv = (CardView) v.findViewById(R.id.cardview);
            textView = (TextView) v.findViewById(R.id.title);
            textView2 = (TextView) v.findViewById(R.id.description);
            imageView = (ImageView) v.findViewById(R.id.newsImage);
            textView3 = (TextView) v.findViewById(R.id.author);
            textView4 = (TextView) v.findViewById(R.id.date);

            v.setOnClickListener(new View.OnClickListener() {
                String url;
                @Override
                public void onClick(View v) {
                    url = art.articleList.get(getAdapterPosition()).get("URL");
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.-->"+url);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment readMoreFragment = new ReadMoreFragment();
                    Bundle args = new Bundle();
                    args.putString("URL",url);
                    readMoreFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, readMoreFragment).commit();
                }
            });

        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param //dataSet String[] containing the data to populate views to be used by RecyclerView.
     */


    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_cardview_row, viewGroup, false);

        return new ViewHolder(v,article);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        //Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTextView().setText(mDataSet[position]);
        imageView.setElevation(20);
        String title =  article.articleList.get(position).get("title");
        String description = article.articleList.get(position).get("description");
        String imageURL = article.articleList.get(position).get("imageURL");
        sourceURL = article.articleList.get(position).get("URL");
        String author = article.articleList.get(position).get("author");
        String date = article.articleList.get(position).get("publishedDate");
        df = new DateFormatter(date);
        Date convertedDate = df.convertDate();
        System.out.println(sourceURL);
        if(imageURL.trim().length() == 0 ){
            imageView.setImageResource(R.drawable.ic_public_black_24dp);
        }else {
            Picasso.with(context).load(imageURL).into(imageView);
        }
        textView.setText(title);
        textView2.setText(description);
        textView3.setText(author);
        textView4.setText(convertedDate.toString());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        System.out.println("------>>>>>>>>>>" + article.getArticleCount());
        return article.getArticleCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
