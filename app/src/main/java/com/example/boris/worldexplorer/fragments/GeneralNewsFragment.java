package com.example.boris.worldexplorer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.GridView;
import android.widget.Toast;

import com.example.boris.worldexplorer.R;
import com.example.boris.worldexplorer.fragments.adapters.CustomGridViewAdapter;


/**
 * Created by boris on 8/9/17.
 */

public class GeneralNewsFragment extends Fragment {

    private Integer[] thumbGeneralID ={R.drawable.bbc,R.drawable.cnn,R.drawable.guardian,R.drawable.hindu,R.drawable.telegraph,
            R.drawable.toi,R.drawable.google_news,R.drawable.time,R.drawable.focus,R.drawable.metro,R.drawable.nytimes,R.drawable.reddit,
            R.drawable.the_huffington_post,R.drawable.washington_post,R.drawable.usa_today,R.drawable.abc_news};

    private String[] newsChannelGeneral = {"BBC NEWS","CNN","GUARDIAN","THE HINDU","THE TELEGRAPH","THE TIMES OF INDIA",
            "GOOGLE NEWS","TIME","FOCUS","METRO","NEW YORK TIMES","REDDIT","THE HUFFINGTON POST","THE WASHINGTON POST","USA TODAY","ABC NEWS"};

    private String[] newsSourceUrl = {"source=bbc-news&sortBy=top&","source=cnn&sortBy=top&","source=the-guardian-uk&sortBy=top&"
                                     ,"source=the-hindu&sortBy=top&","source=the-telegraph&sortBy=top&","source=the-times-of-india&sortBy=top&",
                                      "source=google-news&sortBy=top&","source=time&sortBy=top&","source=focus&sortBy=top&",
                                      "source=metro&sortBy=top&","source=the-new-york-times&sortBy=top&","source=reddit-r-all&sortBy=top&"
                                      ,"source=the-huffington-post&sortBy=top&","source=the-washington-post&sortBy=top&"
                                        ,"source=usa-today&sortBy=top&","source=abc-news-au&sortBy=top&"};


    private String[] channelIDs = {"genBBC0","genCNN1","genGUA2","genHIN3","genTEL4","genTOI5","genGOO6","genTIM7","genFOC8"
                                   ,"genMET9","genNYT10","genRED11","genHUFF12","genWAS13","genUSA14","genABC15"};
    private Integer[] wishListStatus = {0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0};

    //String url = ;


@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View vfrag  = inflater.inflate(R.layout.fragment_generalnews,container,false);
    GridView gridView = (GridView) vfrag.findViewById(R.id.gridview_general);
    gridView.setAdapter(new CustomGridViewAdapter(vfrag.getContext(),newsChannelGeneral,thumbGeneralID,channelIDs,wishListStatus
    ));

    gridView.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v,
                                int position, long id) {

           //Toast.makeText(getActivity(), "hello" + position,Toast.LENGTH_SHORT).show();

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
