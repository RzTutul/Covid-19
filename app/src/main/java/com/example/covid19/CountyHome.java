package com.example.covid19;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covid19.adapter.AllCountryWiseAdpater;
import com.example.covid19.helper.Utils;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.example.covid19.pojo.CountyCaseData;
import com.example.covid19.viwemodel.CovidViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountyHome extends Fragment {

    TextView totalCaseTV,totalRecoverTV,totalDeathsTV,activeCaseTV,criticalCaseTV,todayCaseTV,todayDeathTV,updateTV;

    CovidViewModel covidViewModel;
    ProgressBar dataProgress;

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

        totalCaseTV = view.findViewById(R.id.totalCaseTV);
        totalRecoverTV = view.findViewById(R.id.totalRecoveredTV);
        totalDeathsTV = view.findViewById(R.id.totalDeathsTV);
        activeCaseTV = view.findViewById(R.id.totalActiveCase);
        criticalCaseTV = view.findViewById(R.id.criticalCaseTV);
        todayCaseTV = view.findViewById(R.id.todayCaseTV);
        todayDeathTV = view.findViewById(R.id.todayDeathTV);
        updateTV = view.findViewById(R.id.updateTV);
        dataProgress = view.findViewById(R.id.dataProgress);

        dataProgress.setVisibility(View.VISIBLE);

        covidViewModel.getCountyWiseData("countries/Bangladesh").observe(getActivity(), new Observer<CountryWiseCasePojo>() {
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

            }
        });
    }
}
