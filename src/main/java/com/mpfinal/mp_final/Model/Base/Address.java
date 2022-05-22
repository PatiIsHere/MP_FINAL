package com.mpfinal.mp_final.Model.Base;

import java.io.Serializable;

public final class Address implements Serializable {

    private String cityName;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber = null;

    public Address(){
    }

    public Address(String cityName, String streetName, String houseNumber, String apartmentNumber) {

        this.cityName = cityName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }
    public Address(String cityName, String streetName, String houseNumber) {

        this.cityName = cityName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

//region Getters and Setters
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }
//endregion Getters and Setters

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return "Adress{" +
               "cityname='" + cityName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                (apartmentNumber != null ? ", apartmentNumber=" + apartmentNumber + '\'':"")
                + '}';
    }
}

