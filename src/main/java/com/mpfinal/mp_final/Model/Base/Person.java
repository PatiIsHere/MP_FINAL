package com.mpfinal.mp_final.Model.Base;


import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;

public abstract class Person extends ExtensionManager implements Serializable {
    private String name;
    private String surname;
    private Address address;

    public Person(String name, String surname, Address address) {
        super();
        setName(name);
        setSurname(surname);
        setAddress(address);
    }


//region Getters and Setters
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     * @param surname String
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address.
     * @param address Adress
     */
    public void setAddress(Address address){
        this.address = address;
    }

//endregion Getters and Setters

}
