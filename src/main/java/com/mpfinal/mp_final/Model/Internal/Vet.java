package com.mpfinal.mp_final.Model.Internal;

import com.mpfinal.mp_final.Model.Base.Address;

import java.io.Serializable;
import java.time.LocalDate;

public class Vet extends Employee implements Serializable {
    private boolean bonus;


    public Vet(String name, String surname, LocalDate beginningOfEmployment, LocalDate endOfEmployment, boolean higherEducation) {
        super(name, surname, beginningOfEmployment, endOfEmployment, higherEducation);
        this.bonus = false;
    }
}
