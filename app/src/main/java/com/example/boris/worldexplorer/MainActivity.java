package com.example.boris.worldexplorer;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
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
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
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

    @Override
    public void onBackPressed() {
        if (!getFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }


    //code for handling login option in action Bar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_login:
                    System.out.println("Login Clicked");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                 return  true;
            case R.id.action_bookmark:
                System.out.println("BookMark clicked");
                Intent intent2 = new Intent(this, BookMarkActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);


        }

    }



}
