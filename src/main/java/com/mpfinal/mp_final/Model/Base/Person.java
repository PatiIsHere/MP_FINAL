package com.mpfinal.mp_final.Model.Base;


import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;

public abstract class Person extends ExtensionManager implements Serializable {
    private String name;
    private String surname;
    private Address address;

    public Person(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }
    //TODO - asocjacja Adres 1 - 0.* Osoba PART 1
    public void addAdress(Address address) {
        if (this.address == null) {
            this.address = address;
            address.addPerson(this);
        }
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
