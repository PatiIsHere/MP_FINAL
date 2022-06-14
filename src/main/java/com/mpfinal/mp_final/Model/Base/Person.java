package com.mpfinal.mp_final.Model.Base;


import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;

public abstract class Person extends ExtensionManager implements Serializable {
    private String name;
    private String surname;
    private Address address;

    public Person(String name, String surname, Address address) {
        super();
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    //todo mechanizm aktualizacji miejsca zamieszkania bo bida bez tego

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

//endregion Getters and Setters

}
