package com.example.hostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mynav =findViewById(R.id.bottom_nav);
        mynav.setOnNavigationItemSelectedListener(nav_listner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav_listner= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment=null;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment=new Home();
                    break;
                case R.id.nav_search:
                    selectedFragment=new Search();
                    break;
                case R.id.nav_notification:
                    selectedFragment=new Notification();
                    break;
                case R.id.nav_profile:
                    selectedFragment=new Profile();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}
