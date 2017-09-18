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

    private Integer[] thumbSportsID = {R.drawable.espn,R.drawable.bbc_sport,R.drawable.fox_sport,R.drawable.football_italia,R.drawable.sports_bible,
                                       R.drawable.talk_sport,R.drawable.four_two_four,R.drawable.nfl,R.drawable.espn_cric};

    private String[] newsChannelSports = {"ESPN","BBC SPORT","FOX SPORTS","FOOTBALL ITALIA","THE SPORTS BIBLE","TALKSPORT","FOUR TWO FOUR","NFL NEWS","ESPN CRIC INFO"};

    private String[] newsSourceUrl = {"source=espn&sortBy=top&","source=bbc-sport&sortBy=top&","source=fox-sports&sortBy=top&",
                                      "source=football-italia&sortBy=top&","source=the-sport-bible&sortBy=top&","source=talksport&sortBy=top&","source=four-four-two&sortBy=top&"
                                        ,"source=nfl-news&sortBy=top&","source=espn-cric-info&sortBy=top&"};
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
                fragmentTransaction.addToBackStack(null).commit();

            }
        });

        return vfrag;
    }

}
