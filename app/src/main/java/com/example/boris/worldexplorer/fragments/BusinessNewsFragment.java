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

public class BusinessNewsFragment extends Fragment {

    private Integer[] thumbBusinessID = {R.drawable.business_insider,R.drawable.financial_times,R.drawable.the_economist,
                                            R.drawable.wall_street_journal,R.drawable.cnbc,R.drawable.business_insider_uk};

    private String[] newsChannels = {"BUSINESS INSIDER"," FINANCIAL TIMES","THE ECONOMIST","WALLSTREET JOURNAL","CNBC","BUSINESS INSIDER UK"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vfrag  = inflater.inflate(R.layout.fragment_business,container,false);
        GridView gridView = (GridView) vfrag.findViewById(R.id.gridview_business);
        gridView.setAdapter(new CustomGridViewAdapter(vfrag.getContext(),newsChannels,thumbBusinessID));

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
