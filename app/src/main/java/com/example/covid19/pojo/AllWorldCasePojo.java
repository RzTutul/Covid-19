package com.example.covid19.pojo;

public class AllWorldCasePojo {
    private String cases;

    private String critical;

    private String active;

    private String testsPerOneMillion;

    private String affectedCountries;

    private String recovered;

    private String tests;

    private String deathsPerOneMillion;

    private String casesPerOneMillion;

    private long updated;

    private String deaths;

    private String todayCases;

    private String todayDeaths;

    public String getCases ()
    {
        return cases;
    }

    public void setCases (String cases)
    {
        this.cases = cases;
    }

    public String getCritical ()
    {
        return critical;
    }

    public void setCritical (String critical)
    {
        this.critical = critical;
    }

    public String getActive ()
    {
        return active;
    }

    public void setActive (String active)
    {
        this.active = active;
    }

    public String getTestsPerOneMillion ()
    {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion (String testsPerOneMillion)
    {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public String getAffectedCountries ()
    {
        return affectedCountries;
    }

    public void setAffectedCountries (String affectedCountries)
    {
        this.affectedCountries = affectedCountries;
    }

    public String getRecovered ()
    {
        return recovered;
    }

    public void setRecovered (String recovered)
    {
        this.recovered = recovered;
    }

    public String getTests ()
    {
        return tests;
    }

    public void setTests (String tests)
    {
        this.tests = tests;
    }

    public String getDeathsPerOneMillion ()
    {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion (String deathsPerOneMillion)
    {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public String getCasesPerOneMillion ()
    {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion (String casesPerOneMillion)
    {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public long getUpdated ()
    {
        return updated;
    }

    public void setUpdated (long updated)
    {
        this.updated = updated;
    }

    public String getDeaths ()
    {
        return deaths;
    }

    public void setDeaths (String deaths)
    {
        this.deaths = deaths;
    }

    public String getTodayCases ()
    {
        return todayCases;
    }

    public void setTodayCases (String todayCases)
    {
        this.todayCases = todayCases;
    }

    public String getTodayDeaths ()
    {
        return todayDeaths;
    }

    public void setTodayDeaths (String todayDeaths)
    {
        this.todayDeaths = todayDeaths;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cases = "+cases+", critical = "+critical+", active = "+active+", testsPerOneMillion = "+testsPerOneMillion+", affectedCountries = "+affectedCountries+", recovered = "+recovered+", tests = "+tests+", deathsPerOneMillion = "+deathsPerOneMillion+", casesPerOneMillion = "+casesPerOneMillion+", updated = "+updated+", deaths = "+deaths+", todayCases = "+todayCases+", todayDeaths = "+todayDeaths+"]";
    }
}
