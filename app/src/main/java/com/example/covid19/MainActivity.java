package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.covid19.helper.MyWorker;
import com.example.covid19.helper.UserCountryPreference;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    AHBottomNavigation bottomNavigation;
    boolean isExit = false;
    boolean isBack = false;
    UserCountryPreference userCountryPreference;

    MyConnectivityReceiver myConnectivityReceiver;

    String selectCountyName;

    String[] countryName = {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
            "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
            "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria",
            "Burkina Faso", "Burma (Myanmar)", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
            "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
            "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica",
            "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
            "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia",
            "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
            "Greenland", "Grenada", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
            "Haiti", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica",
            "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait",
            "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
            "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia",
            "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico",
            "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
            "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia",
            "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea",
            "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",
            "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
            "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda",
            "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin",
            "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
            "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea",
            "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland",
            "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau",
            "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
            "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userCountryPreference = new UserCountryPreference(this);
        myConnectivityReceiver = new MyConnectivityReceiver();
        sendNotification();

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
                        String countryName = userCountryPreference.getCountyName();

                        if (countryName.equals("dataIsnotSet")) {
                            showSelectCountryDialog();
                            break;
                        } else {
                            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.countyHome);
                            break;
                        }


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

    private void showSelectCountryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("County ");
        builder.setIcon(R.drawable.ic_flag_black_24dp);
        View view = LayoutInflater.from(this).inflate(R.layout.select_country_dialog, null);

        builder.setView(view);
        Spinner spinner = view.findViewById(R.id.selectCountySp);
        Button saveBtn = view.findViewById(R.id.savedBtn);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryName);
        spinner.setAdapter(countryAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCountyName = parent.getItemAtPosition(position).toString();

                if (selectCountyName.equals("Afghanistan")) {

                } else {
                    Snackbar.make(view, "" + selectCountyName, Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userCountryPreference.setCountyName(selectCountyName);
                dialog.dismiss();
                Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container).navigate(R.id.countyHome);
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
        } else {
            super.onBackPressed();
        }
    }


    class MyConnectivityReceiver extends BroadcastReceiver {
        View parentLayout = findViewById(android.R.id.content);

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

            } else {
                Snackbar.make(parentLayout, "No internet connection!", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myConnectivityReceiver);
    }

    public void sendNotification() {

        Constraints constraints =
                new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

        Data inputData = new Data.Builder()
                .putString("msg", "Covid-19,Checkout today Case!").build();

        ;

        PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest.Builder(MyWorker.class, 1, TimeUnit.DAYS)
                        .setConstraints(constraints)
                        .setInputData(inputData)
                        .build();
        WorkManager.getInstance(this)
                .enqueue(periodicWorkRequest);

    }

}
