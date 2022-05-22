package com.mpfinal.mp_final.Model.ClinicServices;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Appointment implements Serializable {

    private LocalDate dateOfAppointment;
    private int hourOfAppointment;
    private String descriptionOfAppointment;
    private List<MedicalService> medicalServiceList;

    public Appointment(){}

    public Appointment(LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        this.hourOfAppointment = hourOfAppointment;
        this.descriptionOfAppointment = descriptionOfAppointment;
    }

//region Getters and Setters
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public int getHourOfAppointment() {
        return hourOfAppointment;
    }

    public void setHourOfAppointment(int hourOfAppointment) {
        this.hourOfAppointment = hourOfAppointment;
    }

    public String getDescriptionOfAppointment() {
        return descriptionOfAppointment;
    }

    public void setDescriptionOfAppointment(String descriptionOfAppointment) {
        this.descriptionOfAppointment = descriptionOfAppointment;
    }
//endregion Getters and Setters



    //MedicalService jako klasa wewnętrzna bo kompozycja
    public class MedicalService implements Serializable {
        private TypeOfMedicalService typeOfMedicalService;
        private String descriptionOfService;
        private float price;
    }
}
