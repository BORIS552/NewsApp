package com.example.boris.worldexplorer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.boris.worldexplorer.R;

/**
 * Created by boris on 8/20/17.
 */

public class ReadMoreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vfrag = inflater.inflate(R.layout.fragment_readmore,container,false);
        WebView webView = (WebView) vfrag.findViewById(R.id.readmore);
        return vfrag;
    }
}
