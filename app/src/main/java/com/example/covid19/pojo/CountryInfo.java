package com.example.covid19.pojo;

public class CountryInfo {

    private String flag;

    private String _id;

    private String iso2;

    private String lat;

    private String lng;

    private String iso3;

    public String getFlag ()
    {
        return flag;
    }

    public void setFlag (String flag)
    {
        this.flag = flag;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getIso2 ()
    {
        return iso2;
    }

    public void setIso2 (String iso2)
    {
        this.iso2 = iso2;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    public String getLong ()
    {
        return lng;
    }

    public void setLong (String lng)
    {
        this.lng = lng;
    }

    public String getIso3 ()
    {
        return iso3;
    }

    public void setIso3 (String iso3)
    {
        this.iso3 = iso3;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [flag = "+flag+", _id = "+_id+", iso2 = "+iso2+", lat = "+lat+", long = "+lng+", iso3 = "+iso3+"]";
    }
}
