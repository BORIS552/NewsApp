package com.example.boris.worldexplorer.fragments.adapters;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boris.worldexplorer.R;
import com.example.boris.worldexplorer.model.transaction.Article;
import com.squareup.picasso.Picasso;


/**
 * Created by boris on 8/13/17.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static final String TAG = "CustomAdapter";
    private Context context;
    //private String[] mDataSet;
    private Article article;
    private int pos;
    private static TextView textView;
    private static TextView textView2;
    private static ImageView imageView;

    public CustomAdapter() {}

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View v) {
            super(v);
            CustomAdapter adapter = new CustomAdapter();
            final String URL;
            final int position = adapter.pos;
            URL= adapter.article.articleList.get(position).get("URL");
            // Define click listener for the ViewHolder's View.

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");

                }
            });
            textView = (TextView) v.findViewById(R.id.title);
            textView2 = (TextView) v.findViewById(R.id.description);
            imageView = (ImageView) v.findViewById(R.id.newsImage);
        }

         /*public TextView getTextView() {

         return textView;
         } */
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param //dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(Article article, Context context) {
        this.context = context;
        this.article = article;
        setHasStableIds(true);
        //mDataSet = dataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_cardview_row, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
         pos = position;
        //Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTextView().setText(mDataSet[position]);
        imageView.setElevation(20);
        String title =  article.articleList.get(position).get("title");
        String description = article.articleList.get(position).get("description");
        String imageURL = article.articleList.get(position).get("imageURL");
        if(imageURL.trim().length() == 0 ){
            imageView.setImageResource(R.drawable.ic_public_black_24dp);
        }else {
            Picasso.with(context).load(imageURL).into(imageView);
        }
        textView.setText(title);
        textView2.setText(description);
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
