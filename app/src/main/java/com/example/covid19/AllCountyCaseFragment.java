package com.example.covid19;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.covid19.adapter.AllCountryWiseAdpater;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.example.covid19.viwemodel.CovidViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllCountyCaseFragment extends Fragment {

    Button filterBtn;
    ProgressBar dataProgress;
    CovidViewModel covidViewModel;
Spinner selectCountySpinner;
RecyclerView allCountryRV;

List<CountryWiseCasePojo> countryWiseCasePojoArrayList;
String [] countryName={"All Countries","Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
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
            "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe"} ;

    public AllCountyCaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        covidViewModel = ViewModelProviders.of(this).get(CovidViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_county_case, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectCountySpinner = view.findViewById(R.id.selectCountySp);
        allCountryRV = view.findViewById(R.id.allCountryRV);
        dataProgress = view.findViewById(R.id.dataProgress);
        countryWiseCasePojoArrayList = new ArrayList<>();

        dataProgress.setVisibility(View.VISIBLE);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,countryName);

        selectCountySpinner.setAdapter(adapter);



        selectCountySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String countyName = parent.getItemAtPosition(position).toString();

                if (countyName.equals("All Countries"))
                {

                    covidViewModel.getAllCountyWise("countries/").observe(getActivity(), new Observer<List<CountryWiseCasePojo>>() {
                        @Override
                        public void onChanged(List<CountryWiseCasePojo> countryWiseCasePojos) {

                            for (CountryWiseCasePojo countryWiseCasePojo: countryWiseCasePojos)
                            {
                                countryWiseCasePojoArrayList.add(countryWiseCasePojo);
                            }
                            if (countryWiseCasePojos.size()>0)
                            {
                                dataProgress.setVisibility(View.GONE);
                            }
                            AllCountryWiseAdpater allCountryWiseAdpater = new AllCountryWiseAdpater(getActivity(),countryWiseCasePojos);

                            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                            allCountryRV.setLayoutManager(llm);
                            allCountryRV.setAdapter(allCountryWiseAdpater);
                        }
                    });

                }
                else
                {
                    String endurl = String.format("countries/%s",countyName);

                    covidViewModel.getCountyWiseData(endurl).observe(getActivity(), new Observer<CountryWiseCasePojo>() {
                        @Override
                        public void onChanged(CountryWiseCasePojo countryWiseCasePojo) {

                            if (countryWiseCasePojo==null)
                            {
                                Snackbar.make(getView(),"Country not found or doesn't have any cases",Snackbar.LENGTH_SHORT).show();


                            }

                            else
                            {
                                List<CountryWiseCasePojo> list = new ArrayList<>();

                                list.add(countryWiseCasePojo);


                                AllCountryWiseAdpater allCountryWiseAdpater = new AllCountryWiseAdpater(getActivity(),list);

                                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                                allCountryRV.setLayoutManager(llm);
                                allCountryRV.setAdapter(allCountryWiseAdpater);

                            }


                        }
                    });

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





      /*  covidViewModel.getAllCountyWise("countries/").observe(getActivity(), new Observer<List<CountryWiseCasePojo>>() {
            @Override
            public void onChanged(List<CountryWiseCasePojo> countryWiseCasePojos) {

                if (countryWiseCasePojos.size()>0)
                {
                    dataProgress.setVisibility(View.GONE);
                }
                AllCountryWiseAdpater allCountryWiseAdpater = new AllCountryWiseAdpater(getActivity(),countryWiseCasePojos);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                allCountryRV.setLayoutManager(llm);
                allCountryRV.setAdapter(allCountryWiseAdpater);
            }
        });
*/


    }
}
