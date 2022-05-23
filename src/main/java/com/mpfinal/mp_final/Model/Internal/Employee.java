package com.mpfinal.mp_final.Model.Internal;

import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Employee extends Person implements Serializable {
    private LocalDate beginningOfEmployment;
    private LocalDate endOfEmployment;
    private boolean higherEducation;
    //todo w UML nanieść korekty pesel i /dataUrodzenia

    public Employee(String name, String surname, LocalDate beginningOfEmployment, LocalDate endOfEmployment, boolean higherEducation) {
        super(name, surname);
        this.beginningOfEmployment = beginningOfEmployment;
        this.endOfEmployment = endOfEmployment;
        this.higherEducation = higherEducation;
    }

//region Getters and Setters
    public LocalDate getBeginningOfEmployment() {
        return beginningOfEmployment;
    }

    public void setBeginningOfEmployment(LocalDate beginningOfEmployment) {
        this.beginningOfEmployment = beginningOfEmployment;
    }

    public LocalDate getEndOfEmployment() {
        return endOfEmployment;
    }

    public void setEndOfEmployment(LocalDate endOfEmployment) {
        this.endOfEmployment = endOfEmployment;
    }

    public boolean isHigherEducation() {
        return higherEducation;
    }

    public void setHigherEducation(boolean higherEducation) {
        this.higherEducation = higherEducation;
    }

//endregion Getters and Setters

    public int getBaseVacationDays(){
        if(this.endOfEmployment != null){
            return 0;
        }

        int baseVacationDays = 6;
        if (higherEducation){
            baseVacationDays += 2;
        }

        return (int) (baseVacationDays + ChronoUnit.YEARS.between(beginningOfEmployment, LocalDate.now()));
    }
}
