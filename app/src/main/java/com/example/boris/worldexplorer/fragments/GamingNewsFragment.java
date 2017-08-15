package com.example.boris.worldexplorer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.boris.worldexplorer.R;
import com.example.boris.worldexplorer.fragments.adapters.CustomGridViewAdapter;

/**
 * Created by boris on 8/9/17.
 */

public class GamingNewsFragment extends Fragment {

    private Integer[] thumbGamingID = {R.drawable.ign,R.drawable.polygon,R.drawable.verge};
    private String[] newsChannelGaming = {"IGN","POLYGON","THE VERGE"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vfrag  = inflater.inflate(R.layout.fragment_gaming,container,false);
        GridView gridView = (GridView) vfrag.findViewById(R.id.gridview_gaming);
        gridView.setAdapter(new CustomGridViewAdapter(vfrag.getContext(),newsChannelGaming,thumbGamingID));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "hello" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return vfrag;
    }
}
