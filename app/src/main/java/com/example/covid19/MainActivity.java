package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {
private NavController navController;
    AHBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        navController = Navigation.findNavController(this,R.id.nav_host_fragment_container);


// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.worldicon, R.color.transparent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.homeCounty, R.drawable.homecountyicon, R.color.colorPrimaryDark);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.allCountry, R.drawable.symtomicon, R.color.colorBottomNavigationAccent);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);


        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        //if need
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(0);


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...

                switch (position) {
                    case 0:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.homeFragment);
                        break;
                    case 1:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.countyHome);
                        break;


                }

                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId())
                {
                    case R.id.spalshScreen:
                        bottomNavigation.setVisibility(View.GONE);
                        break;
                    case R.id.homeFragment:
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                bottomNavigation.setVisibility(View.VISIBLE);

                            }
                        });
                        break;

                        default:
                }
            }
        });
    }



}
