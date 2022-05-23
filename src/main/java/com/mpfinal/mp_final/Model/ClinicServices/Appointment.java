package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Appointment extends ExtensionManager implements Serializable {

    private LocalDate dateOfAppointment;
    private int hourOfAppointment;
    private String descriptionOfAppointment;
    private List<MedicalService> medicalServiceList = new ArrayList<>();
    private static Set<MedicalService> allMedicalServices = new HashSet<>();

    public Appointment(LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) {
        super();
        this.dateOfAppointment = dateOfAppointment;
        this.hourOfAppointment = hourOfAppointment;
        this.descriptionOfAppointment = descriptionOfAppointment;
    }
    public void addMedicalService(MedicalService medicalService) throws Exception {
        if(!medicalServiceList.contains(medicalService)) {
            // Check if the part has been already added to any wholes
            if(allMedicalServices.contains(medicalService)) {
                throw new Exception("The part is already connected with a whole!");
            }

            medicalServiceList.add(medicalService);

            // Store on the list of all parts
            allMedicalServices.add(medicalService);
        }
    }
    public MedicalService getMedSer(){
        return medicalServiceList.get(0);
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
