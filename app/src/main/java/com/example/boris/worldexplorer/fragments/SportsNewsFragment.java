package com.example.boris.worldexplorer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class SportsNewsFragment extends Fragment {

    private Integer[] thumbSportsID = {R.drawable.espn,R.drawable.espn_cric,R.drawable.fox_sport,R.drawable.football_italia,R.drawable.sports_bible,R.drawable.talk_sport};

    private String[] newsChannelSports = {"ESPN","ESPN CRIC INFO","FOX SPORTS","FOOTBALL ITALIA","THE SPORTS BIBLE","TALKSPORT"};

    private String[] newsSourceUrl = {};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vfrag  = inflater.inflate(R.layout.fragment_sportsnews,container,false);
        GridView gridView = (GridView) vfrag.findViewById(R.id.gridview_sports);
        gridView.setAdapter(new CustomGridViewAdapter(vfrag.getContext(),newsChannelSports,thumbSportsID));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

               // Toast.makeText(getActivity(), "hello" + position,Toast.LENGTH_SHORT).show();

                RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putString("URL", newsSourceUrl[position]);
                recyclerViewFragment.setArguments(args);
                fragmentTransaction.replace(R.id.main_container,recyclerViewFragment);
                fragmentTransaction.commit();

            }
        });

        return vfrag;
    }

}
