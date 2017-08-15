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

public class TechnologyNewsFragment extends Fragment {

    private Integer[] thumbTechnologyID = {R.drawable.engadget,R.drawable.tech_crunch,R.drawable.tech_radar,R.drawable.recode,
                                            R.drawable.new_scientist,R.drawable.mashable,R.drawable.hacker_news,R.drawable.the_next_web};

    private String[] newsChannels = {"ENGADGET","TECHCRUNCH","TECHRADAR","RECODE","NEW SCIENTIST","MASHABLE","HACKER NEWS","THE NEXT WEB"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vfrag  = inflater.inflate(R.layout.fragment_technology,container,false);
        GridView gridView = (GridView) vfrag.findViewById(R.id.gridview_technology);
        gridView.setAdapter(new CustomGridViewAdapter(vfrag.getContext(),newsChannels,thumbTechnologyID));

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
