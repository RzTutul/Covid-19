package com.example.covid19.pojo;

public class AllCountyCaseData {
   String flag;
   String Cases;
   String todayCases;
   String deaths;
   String todayDaths;
   String recovered;
   String active;
   String critical;
   String tests;
   String country;

    public AllCountyCaseData() {
    }

    public AllCountyCaseData(String flag, String cases, String todayCases, String deaths, String todayDaths, String recovered, String active, String critical, String tests, String country) {
        this.flag = flag;
        Cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDaths = todayDaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.tests = tests;
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCases() {
        return Cases;
    }

    public void setCases(String cases) {
        Cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDaths() {
        return todayDaths;
    }

    public void setTodayDaths(String todayDaths) {
        this.todayDaths = todayDaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
