package com.mpfinal.mp_final.Model.Animals;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicalCard implements Serializable {
    private LocalDate registrationDate;
    private int age;

    public MedicalCard(){}

    public MedicalCard(LocalDate registrationDate, int age) {
        this.registrationDate = registrationDate;
        this.age = age;
    }

//region Getters and Setters
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//endregion Getters and Setters

}
