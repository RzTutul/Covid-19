package com.example.covid19.viwemodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid19.pojo.AllWorldCasePojo;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.example.covid19.repos.CovidPepos;

public class CovidViewModel extends ViewModel {

    CovidPepos covidPepos ;
    public CovidViewModel() {
     covidPepos = new CovidPepos();
    }

    public MutableLiveData<CountryWiseCasePojo> getCountyWiseData(String endUrl)
    {
        return covidPepos.getCountyWiseInfo(endUrl);
    }
    public MutableLiveData<AllWorldCasePojo> getAllworldWiseData(String endUrl)
    {
        return covidPepos.getAllWorldWise(endUrl);
    }


}
