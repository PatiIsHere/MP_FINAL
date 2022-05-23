package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Appointment extends ExtensionManager implements Serializable {

    private LocalDate dateOfAppointment;
    private int hourOfAppointment;
    private String descriptionOfAppointment;
    private List<MedicalService> medicalServiceList;

    public Appointment(LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) {
        super();
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


}
