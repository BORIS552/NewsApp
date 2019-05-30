package com.example.boris.worldexplorer.fragments.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private static Button bookmark;
    private String sourceURL;

    //default
    public CustomAdapter() {}


    public CustomAdapter(Article article, Context context) {
        this.context = context;
        this.article = article;
        setHasStableIds(true);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public static CardView cv;

        public ViewHolder(final View v, final Article art) {
            super(v);
            cv = (CardView) v.findViewById(R.id.cardview);
            textView = (TextView) v.findViewById(R.id.title);
            textView2 = (TextView) v.findViewById(R.id.description);
            imageView = (ImageView) v.findViewById(R.id.newsImage);
            textView3 = (TextView) v.findViewById(R.id.author);
            textView4 = (TextView) v.findViewById(R.id.date);
            //bookmark = (Button) v.findViewById(R.id.bookmark);

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
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, readMoreFragment).addToBackStack(null).commit();
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                PopupMenu popup = new PopupMenu(v.getContext(),cv);

                @Override
                public boolean onLongClick(View view){

                    return true;
                }
            });

        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_cardview_row, viewGroup, false);

        return new ViewHolder(v,article);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        imageView.setElevation(20);
        String title =  article.articleList.get(position).get("title");
        title = testNull(title);
        String description = article.articleList.get(position).get("description");
        description = testNull(description);
        String imageURL = article.articleList.get(position).get("imageURL");
        sourceURL = article.articleList.get(position).get("URL");
        String author = article.articleList.get(position).get("author");
        author = testNull(author);
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
//        bookmark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Adding the news to bookmark");
//                bookmark.setBackground(v.getResources().getDrawable(R.drawable.ic_turned_in_black_24dp));
//            }
//        });
    }

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

    private String testNull(String text){
        if(text == null)
            text  = " ";
        else if(text.equals("null"))
            text = " ";
        return text;
    }

}
