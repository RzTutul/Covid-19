package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.content.DialogInterface;
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
    boolean isExit = false;
    boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);


// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.worldicon, R.color.transparent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.homeCounty, R.drawable.homecountyicon, R.color.colorPrimaryDark);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.allCountry, R.drawable.allcountyicon, R.color.flag);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.symptoms, R.drawable.symtomicon, R.color.transparent);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.info, R.drawable.ic_info_outline_black_24dp, R.color.info);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);


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
                    case 2:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.allCountyCaseFragment);
                        break;
                    case 3:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.symtomFragment);

                        break;
                    case 4:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.aboutFragment);

                        break;

                        default:
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
                switch (destination.getId()) {
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
                        isExit = true;

                        break;
                    case R.id.countyHome:
                        isExit = true;
                        break;
                    case R.id.allCountyCaseFragment:
                        isExit = true;

                        break;
                    case R.id.symtomFragment:
                        isExit = true;

                        break;
                    case R.id.aboutFragment:
                        isExit = true;
                        isBack = true;
                        break;


                    default:
                        isExit = false;
                        break;
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

        if (isExit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_exit_to_app_black_24dp);
            builder.setTitle("Do you want to exit?");
            builder.setMessage("Covid-19 Stay Safe!")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            super.onBackPressed();
        }
    }

}
