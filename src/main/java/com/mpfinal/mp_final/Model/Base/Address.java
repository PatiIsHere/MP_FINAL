package com.mpfinal.mp_final.Model.Base;

import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Address extends ExtensionManager implements Serializable {

    private String cityName;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber = null;
    private List<Person> persons = new ArrayList<>();

    public Address(String cityName, String streetName, String houseNumber, String apartmentNumber) {
        super();
        this.cityName = cityName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }
    public Address(String cityName, String streetName, String houseNumber) {
        super();
        this.cityName = cityName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    //TODO - asocjacja Adres 1 - 0.* Osoba PART 2
    public void addPerson(Person person){
       if(!persons.contains(person)) {
           persons.add(person);
           person.addAdress(this);
       }
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

    public List<Person> getPersons(){
        return this.persons;
    }
//endregion Getters and Setters

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    //TODO - loop print osoby zamieszkałe pod tym adresem
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

