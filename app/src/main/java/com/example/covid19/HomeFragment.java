package com.example.covid19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covid19.adapter.AllCountryWiseAdpater;
import com.example.covid19.adapter.AllWorldWiseAdapter;
import com.example.covid19.helper.Utils;
import com.example.covid19.pojo.AllWorldCasePojo;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.example.covid19.pojo.CountyCaseData;
import com.example.covid19.viwemodel.CovidViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    CovidViewModel covidViewModel;
    RecyclerView totalWorldCaseRV, totdayTotalRV;
    TextView updatedTV;
    ProgressBar dataProgressBar;


    AllWorldWiseAdapter allWorldWiseAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        covidViewModel = ViewModelProviders.of(this).get(CovidViewModel.class);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalWorldCaseRV = view.findViewById(R.id.recyclerviewtotalworld);
        totdayTotalRV = view.findViewById(R.id.recyclerviewtotaltoday);
        updatedTV = view.findViewById(R.id.updateTV);
        dataProgressBar = view.findViewById(R.id.dataProgress);

        dataProgressBar.setVisibility(View.VISIBLE);




        covidViewModel.getAllworldWiseData("all").observe(getActivity(), new Observer<AllWorldCasePojo>() {
            @Override
            public void onChanged(AllWorldCasePojo allWorldCasePojo) {


                updatedTV.setText(Utils.getDateFormat(allWorldCasePojo.getUpdated()));

                List<CountyCaseData> list = new ArrayList<>();

                CountyCaseData worldtotalCase = new CountyCaseData();
                worldtotalCase.setHeader("Total Case");
                worldtotalCase.setValue(allWorldCasePojo.getCases());
                worldtotalCase.setColor("#2d7a8a");
                list.add(worldtotalCase);

                if (0<list.size())
                {
                    dataProgressBar.setVisibility(View.GONE);
                }

                CountyCaseData worldTotalDeath = new CountyCaseData();
                worldTotalDeath.setHeader("Total Death");
                worldTotalDeath.setValue(allWorldCasePojo.getDeaths());
                worldTotalDeath.setColor("#FF0000");
                list.add(worldTotalDeath);

                CountyCaseData worldtotalRecovery = new CountyCaseData();
                worldtotalRecovery.setHeader("Total Recovery");
                worldtotalRecovery.setValue(allWorldCasePojo.getRecovered());
                worldtotalRecovery.setColor("#7CFC00");
                list.add(worldtotalRecovery);

                CountyCaseData worldTotalActive = new CountyCaseData();
                worldTotalActive.setHeader("Total Active");
                worldTotalActive.setValue(allWorldCasePojo.getActive());
                worldTotalActive.setColor("#00FF7F");
                list.add(worldTotalActive);

                CountyCaseData critical = new CountyCaseData();
                critical.setHeader("Critical Patient");
                critical.setValue(allWorldCasePojo.getCritical());
                critical.setColor("#FF1493");
                list.add(critical);

                CountyCaseData totalTests = new CountyCaseData();
                totalTests.setHeader("Total Tests");
                totalTests.setValue(allWorldCasePojo.getTests());
                totalTests.setColor("#87CEFA");
                list.add(totalTests);

                CountyCaseData deathPermillin = new CountyCaseData();
                deathPermillin.setHeader("Death Per Million");
                deathPermillin.setValue(allWorldCasePojo.getDeathsPerOneMillion());
                deathPermillin.setColor("#FFD700");
                list.add(deathPermillin);

                CountyCaseData casePerMillion = new CountyCaseData();
                casePerMillion.setHeader("Case Per Million");
                casePerMillion.setValue(allWorldCasePojo.getCasesPerOneMillion());
                casePerMillion.setColor("#FFA07A");
                list.add(casePerMillion);

                CountyCaseData worltotalConty = new CountyCaseData();
                worltotalConty.setHeader("Total County");
                worltotalConty.setValue(allWorldCasePojo.getAffectedCountries());
                worltotalConty.setColor("#40E0D0");
                list.add(worltotalConty);

               allWorldWiseAdapter = new AllWorldWiseAdapter(getActivity(), list);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                totalWorldCaseRV.setLayoutManager(llm);
                totalWorldCaseRV.setAdapter(allWorldWiseAdapter);
                allWorldWiseAdapter.notifyDataSetChanged();

            }
        });


        covidViewModel.getAllworldWiseData("all").observe(getActivity(), new Observer<AllWorldCasePojo>() {
            @Override
            public void onChanged(AllWorldCasePojo allWorldCasePojo) {


                List<CountyCaseData> list1 = new ArrayList<>();

                CountyCaseData worldtotalCase = new CountyCaseData();
                worldtotalCase.setHeader("Today Case");
                worldtotalCase.setValue(allWorldCasePojo.getTodayCases());
                worldtotalCase.setColor("#00BFFF");
                list1.add(worldtotalCase);

                CountyCaseData worldTotalDeath = new CountyCaseData();
                worldTotalDeath.setHeader("Today Death");
                worldTotalDeath.setValue(allWorldCasePojo.getTodayDeaths());
                worldTotalDeath.setColor("#c74b1e");
                list1.add(worldTotalDeath);

              allWorldWiseAdapter = new AllWorldWiseAdapter(getActivity(), list1);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

                totdayTotalRV.setLayoutManager(llm);

                totdayTotalRV.setAdapter(allWorldWiseAdapter);
                allWorldWiseAdapter.notifyDataSetChanged();


            }
        });
        updatedTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataProgressBar.setVisibility(View.VISIBLE);


                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        threaMethod();
                      updateData();
                    }
                });
                thread.start();


            }
        });



    }

    private void updateData() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //do stuff like remove view etc
                allWorldWiseAdapter.notifyDataSetChanged();
                dataProgressBar.setVisibility(View.GONE);
            }
        });


    }

    private void threaMethod() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
