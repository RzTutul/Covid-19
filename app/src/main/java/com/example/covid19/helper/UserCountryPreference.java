package com.example.covid19.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCountryPreference {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public UserCountryPreference(Context context) {
        preferences = context.getSharedPreferences("covid19",Context.MODE_PRIVATE);

        editor = preferences.edit();
    }


    public void setCountyName(String countyName)
    {
        editor.putString("name",countyName);
        editor.commit();
    }
    public String getCountyName()
    {
        return preferences.getString("name","dataIsnotSet");
    }

}
