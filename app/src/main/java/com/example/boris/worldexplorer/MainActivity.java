package com.example.boris.worldexplorer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.boris.worldexplorer.fragments.BusinessNewsFragment;
import com.example.boris.worldexplorer.fragments.GamingNewsFragment;
import com.example.boris.worldexplorer.fragments.GeneralNewsFragment;
import com.example.boris.worldexplorer.fragments.SportsNewsFragment;
import com.example.boris.worldexplorer.fragments.TechnologyNewsFragment;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentTransaction = null;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_general:
                    fragmentTransaction.replace(R.id.main_container, new GeneralNewsFragment()).commit();
                    return true;
                case R.id.navigation_sports:
                    fragmentTransaction.replace(R.id.main_container, new SportsNewsFragment()).commit();
                    return true;
                case R.id.navigation_technology:
                    fragmentTransaction.replace(R.id.main_container, new TechnologyNewsFragment()).commit();
                    return true;
                case R.id.navigation_business:
                    fragmentTransaction.replace(R.id.main_container, new BusinessNewsFragment()).commit();
                    return true;
                case R.id.navigation_gaming:
                    fragmentTransaction.replace(R.id.main_container, new GamingNewsFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //
        fragmentTransaction = null;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container,new GeneralNewsFragment()).commit();
    }



}
