package com.example.boris.worldexplorer.model.transaction;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by boris on 8/11/17.
 */

public class Article {

    public String status,source,sortBy;
    public int articleCount;
    public ArrayList<HashMap<String,String>> articleList;

    public Article() {
    }

    public String getStatus() {
        return this.status;
    }

    public String getSource() {
        return this.source;
    }

    public int getArticleCount() {
        return this.articleCount;
    }
    public ArrayList<HashMap<String,String>> getArticleList() {
        return this.articleList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setArticleList(ArrayList<HashMap<String,String>> articleList) {
        this.articleList = articleList;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }
}
