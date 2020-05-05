package com.example.covid19.serviceapi;

import com.example.covid19.pojo.AllWorldCasePojo;
import com.example.covid19.pojo.CountryWiseCasePojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CovidInfoServiceApi {

    @GET
    Call<CountryWiseCasePojo> getCountryWiseCase(@Url String endUrl);

    @GET
    Call<AllWorldCasePojo> getAllWorldWise(@Url String endUrl);


}
