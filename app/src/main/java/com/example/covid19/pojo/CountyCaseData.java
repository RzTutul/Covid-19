package com.example.covid19.pojo;

public class CountyCaseData {
    String header;
    String value;
    String color;

    public CountyCaseData() {
    }

    public CountyCaseData(String header, String value, String color) {
        this.header = header;
        this.value = value;
        this.color = color;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
