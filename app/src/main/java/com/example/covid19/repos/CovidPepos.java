package com.example.covid19.repos;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.covid19.helper.RetrofitClient;
import com.example.covid19.pojo.AllWorldCasePojo;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.example.covid19.serviceapi.CovidInfoServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class CovidPepos {

    MutableLiveData<CountryWiseCasePojo> countryWiseLD = new MutableLiveData<>();
    MutableLiveData<AllWorldCasePojo> allWorldWiseLD = new MutableLiveData<>();


    public MutableLiveData<CountryWiseCasePojo> getCountyWiseInfo(String endurl)
    {

        CovidInfoServiceApi covidInfoServiceApi = RetrofitClient.getRetrofitClient().create(CovidInfoServiceApi.class);

        covidInfoServiceApi.getCountryWiseCase(endurl).enqueue(new Callback<CountryWiseCasePojo>() {
            @Override
            public void onResponse(Call<CountryWiseCasePojo> call, Response<CountryWiseCasePojo> response) {

                countryWiseLD.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CountryWiseCasePojo> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });

        return countryWiseLD;
    }
    public MutableLiveData<AllWorldCasePojo> getAllWorldWise(String endurl)
    {

        CovidInfoServiceApi covidInfoServiceApi = RetrofitClient.getRetrofitClient().create(CovidInfoServiceApi.class);

        covidInfoServiceApi.getAllWorldWise(endurl).enqueue(new Callback<AllWorldCasePojo>() {
            @Override
            public void onResponse(Call<AllWorldCasePojo> call, Response<AllWorldCasePojo> response) {

                allWorldWiseLD.postValue(response.body());
            }

            @Override
            public void onFailure(Call<AllWorldCasePojo> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });

        return allWorldWiseLD;
    }


}
