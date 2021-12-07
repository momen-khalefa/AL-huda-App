package com.alhud.alhudastore;

public class fuel_prices {
    int year;
    int l_year;
    int month;
    float gasoline_price;
    float solar_price;
    float kaz_price;
    float gaz_price;

    public fuel_prices(int year ,int l_year,int month, float gasoline_price, float solar_price,float kaz_price,float gaz_price){
        this.year=year;
        this.l_year=l_year;
        this.month=month;
        this.gasoline_price=gasoline_price;
        this.solar_price=solar_price;
        this.kaz_price=kaz_price;
        this.gaz_price=gaz_price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setL_year(int l_year) {
        this.l_year = l_year;
    }

    public int getL_year() {
        return l_year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setGaz_price(float gaz_price) {
        this.gaz_price = gaz_price;
    }

    public float getGaz_price() {
        return gaz_price;
    }

    public void setGasoline_price(float gasoline_price) {
        this.gasoline_price = gasoline_price;
    }

    public float getGasoline_price() {
        return gasoline_price;
    }

    public void setSolar_price(float solar_price) {
        this.solar_price = solar_price;
    }

    public float getSolar_price() {
        return solar_price;
    }

    public void setKaz_price(float kaz_price) {
        this.kaz_price = kaz_price;
    }

    public float getKaz_price() {
        return kaz_price;
    }
}
