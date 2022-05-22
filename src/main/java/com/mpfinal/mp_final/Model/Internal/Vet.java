package com.mpfinal.mp_final.Model.Internal;

import com.mpfinal.mp_final.Model.Base.Address;

import java.time.LocalDate;

public class Vet extends Employee{
    private boolean bonus;

    public Vet() {
    }

    public Vet(String name, String surname, Address address, LocalDate beginningOfEmployment, LocalDate endOfEmployment, boolean higherEducation) {
        super(name, surname, address, beginningOfEmployment, endOfEmployment, higherEducation);
        this.bonus = false;
    }
}
