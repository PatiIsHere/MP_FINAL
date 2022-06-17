package com.mpfinal.mp_final.Model.Base;

import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Address extends ExtensionManager implements Serializable {

    private String cityName;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber;

    /**
     * Base constructor.
     * @param cityName String
     * @param streetName String
     * @param houseNumber String
     */
    public Address(String cityName, String streetName, String houseNumber) {
        super();
        setCityName(cityName);
        setStreetName(streetName);
        setHouseNumber(houseNumber);
    }

    /**
     * Constructor when apartmentNumber is provided.
     * @param cityName String
     * @param streetName String
     * @param houseNumber String
     * @param apartmentNumber String
     */
    public Address(String cityName, String streetName, String houseNumber, String apartmentNumber) {
        this(cityName, streetName, houseNumber);
        setApartmentNumber(apartmentNumber);
    }


//region Getters and Setters
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the city name.
     * @param cityName String
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the street name.
     * @param streetName String
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the house number.
     * @param houseNumber String
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    /**
     * Sets the apartment number.
     * @param apartmentNumber
     */
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
    //endregion Getters and Setters

}

