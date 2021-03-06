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

public class GamingNewsFragment extends Fragment {

    private Integer[] thumbGamingID = {R.drawable.ign,R.drawable.polygon,R.drawable.verge};
    private String[] newsChannelGaming = {"IGN","POLYGON","THE VERGE"};
    private String[] newsSourceUrl = {"source=ign&sortBy=top&","source=polygon&sortBy=top&","source=the-verge&sortBy=top&"};

    //dummy
    private String[] channelIDs = {"genBBC0","genCNN1","genGUA2","genHIN3"};
    private Integer[] wishListStatus = {0,1,1,0};
    //val

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vfrag  = inflater.inflate(R.layout.fragment_gaming,container,false);
        GridView gridView = (GridView) vfrag.findViewById(R.id.gridview_gaming);
        gridView.setAdapter(new CustomGridViewAdapter(vfrag.getContext(),newsChannelGaming,thumbGamingID,channelIDs,wishListStatus));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
               // Toast.makeText(getActivity(), "hello" + position, Toast.LENGTH_SHORT).show();

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
