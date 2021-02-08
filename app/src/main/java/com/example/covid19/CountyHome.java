package com.example.covid19;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.covid19.adapter.AllCountryWiseAdpater;
import com.example.covid19.helper.UserCountryPreference;
import com.example.covid19.helper.Utils;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.example.covid19.pojo.CountyCaseData;
import com.example.covid19.viwemodel.CovidViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountyHome extends Fragment {

    TextView countyNameTV, totalCaseTV,totalRecoverTV,totalDeathsTV,activeCaseTV,criticalCaseTV,todayCaseTV,todayDeathTV,updateTV;

    CovidViewModel covidViewModel;
    ProgressBar dataProgress;
    CircularImageView flagimage;

    String countryName,selectCountyName;

    Button showDialogBtn,webBtn;

    UserCountryPreference userCountryPreference;


    String[] countryNameList = {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
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

    public CountyHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        covidViewModel = ViewModelProviders.of(this).get(CovidViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_county_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userCountryPreference = new UserCountryPreference(getActivity());
        countryName = userCountryPreference.getCountyName();
        countyNameTV = view.findViewById(R.id.countyName);
        totalCaseTV = view.findViewById(R.id.totalCaseTV);
        totalRecoverTV = view.findViewById(R.id.totalRecoveredTV);
        totalDeathsTV = view.findViewById(R.id.totalDeathsTV);
        activeCaseTV = view.findViewById(R.id.totalActiveCase);
        criticalCaseTV = view.findViewById(R.id.criticalCaseTV);
        todayCaseTV = view.findViewById(R.id.todayCaseTV);
        todayDeathTV = view.findViewById(R.id.todayDeathTV);
        updateTV = view.findViewById(R.id.updateTV);
        dataProgress = view.findViewById(R.id.dataProgress);
        flagimage = view.findViewById(R.id.flagimage);
        showDialogBtn = view.findViewById(R.id.showDialogBtn);
        webBtn = view.findViewById(R.id.webBtn);

        dataProgress.setVisibility(View.VISIBLE);

        countyNameTV.setText(countryName);


        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectCountryDialog();
            }
        });

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnlineWebView.countyName = countryName;

                Navigation.findNavController(view).navigate(R.id.onlineWebView);

            }
        });

        covidViewModel.getCountyWiseData(String.format("countries/%s",countryName)).observe(getActivity(), new Observer<CountryWiseCasePojo>() {
            @Override
            public void onChanged(CountryWiseCasePojo countryWiseCasePojo) {

                if (0<countryWiseCasePojo.getUpdated())
                {
                    dataProgress.setVisibility(View.GONE);
                }

                totalCaseTV.setText(countryWiseCasePojo.getCases());
                totalRecoverTV.setText(countryWiseCasePojo.getRecovered());
                totalDeathsTV.setText(countryWiseCasePojo.getDeaths());
                activeCaseTV.setText(countryWiseCasePojo.getActive());
                criticalCaseTV.setText(countryWiseCasePojo.getCritical());
                todayCaseTV.setText(countryWiseCasePojo.getTodayCases());
                todayDeathTV.setText(countryWiseCasePojo.getTodayDeaths());
                updateTV.setText(Utils.getDateFormat(countryWiseCasePojo.getUpdated()));
                Picasso.get().load(countryWiseCasePojo.getCountryInfo().getFlag()).into(flagimage);


            }
        });
    }

    private void showSelectCountryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("County ");
        builder.setIcon(R.drawable.ic_flag_black_24dp);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.select_country_dialog, null);

        builder.setView(view);
        Spinner spinner = view.findViewById(R.id.selectCountySp);
        Button saveBtn = view.findViewById(R.id.savedBtn);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, countryNameList);
        spinner.setAdapter(countryAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCountyName = parent.getItemAtPosition(position).toString();

                if (selectCountyName.equals("Afghanistan"))
                {

                }
                else
                {
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
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_container).navigate(R.id.countyHome);
            }
        });


    }


}
