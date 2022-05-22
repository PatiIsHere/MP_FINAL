package com.mpfinal.mp_final.Model.Base;


import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String surname;
    private Address address;

    public Person(){
    }

    public Person(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }
//region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
//endregion Getters and Setters

}
