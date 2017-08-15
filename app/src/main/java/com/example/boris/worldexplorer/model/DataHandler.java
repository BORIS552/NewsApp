package com.example.boris.worldexplorer.model;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.boris.worldexplorer.fragments.adapters.CustomAdapter;
import com.example.boris.worldexplorer.model.transaction.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by boris on 8/12/17.
 */

public class DataHandler{

    private final String BASE_URL = "https://newsapi.org/v1/articles?";
    private Context context;
    private String api_Key = "apiKey=ee5791b88e6949788adb0b5957c4c331";
    private RequestQueue requestQueue;
    private int articleCount;
    private String author,title,description,URL,imageURL,date;
    public  ArrayList<HashMap<String,String>> articleList;
    public Article article = new Article();
    public CustomAdapter adapter;

    //constructor initialiaing variables.
    public DataHandler(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(this.context);
    }




        public void fetchData(String url, RecyclerView mRecyclerView, CustomAdapter mAdapter) {
            this.adapter = mAdapter;
            final ProgressDialog loading = ProgressDialog.show(context,"Loading Data", "Please wait...",false,false);
            articleList = new ArrayList<>();
            this.URL = BASE_URL+url+api_Key;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String status = response.getString("status");
                                article.setStatus(status);
                                System.out.println(status);
                                String source = response.getString("source");
                                article.setSource(source);
                                System.out.println(source);
                                JSONArray newsArticles = response.getJSONArray("articles");
                                articleCount = newsArticles.length();
                                System.out.println(articleCount);
                                article.setArticleCount(articleCount);
                                //loop for parsing JSONArray in the main JSONObject
                                for (int i = 0; i < newsArticles.length(); i++) {
                                    JSONObject obj = newsArticles.getJSONObject(i);
                                    System.out.println("JSONArray Object"+i);
                                    author = obj.getString("author");
                                    System.out.println(author);
                                    title = obj.getString("title");
                                    System.out.println(title);
                                    description = obj.getString("description");
                                    System.out.println(description);
                                    URL = obj.getString("url");
                                    System.out.println(URL);
                                    imageURL = obj.getString("urlToImage");
                                    System.out.println(imageURL);
                                    date = obj.getString("publishedAt");
                                    System.out.println(date);
                                    HashMap<String, String> news = new HashMap<>();
                                    news.put("author", author);
                                    news.put("title", title);
                                    news.put("description", description);
                                    news.put("URL", URL);
                                    news.put("imageURL", imageURL);
                                    news.put("publishedDate", date);

                                    articleList.add(news);
                                    loading.dismiss();
                                } // end of for loop.

                                article.setArticleList(articleList);
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            requestQueue.add(jsonObjectRequest);
            adapter = new CustomAdapter(article);
            mRecyclerView.setAdapter(adapter);
        }
        /*
       public Article getObject() {
            return article;
        }*/

}
