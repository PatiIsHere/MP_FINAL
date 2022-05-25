package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.Internal.Receptionist;
import com.mpfinal.mp_final.Model.Internal.Vet;
import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Appointment extends ExtensionManager implements Serializable {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private int appointmentID;

    private LocalDate dateOfAppointment;
    private int hourOfAppointment;
    private String descriptionOfAppointment;

    private Client assignedClient;

    private List<MedicalService> medicalServiceList = new ArrayList<>();
    private static Set<MedicalService> allMedicalServices = new HashSet<>();

    private Appointment(LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) {
        super();
        this.dateOfAppointment = dateOfAppointment;
        this.hourOfAppointment = hourOfAppointment;
        this.descriptionOfAppointment = descriptionOfAppointment;
        appointmentID = idGenerator.incrementAndGet();
    }
    //Appointment can only be created by Receptionist with assigned Vet and Client
    public static Appointment createAppointment(Receptionist receptionist, Vet vet, Client client
            , LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment){
        if (receptionist == null || vet == null || client == null){
            return null; //TOD throw exception
        }
        //tod validate if vet is avaible in provided time and if provided client don't have another
        //appointment at provided time
        Appointment appointment = new Appointment(dateOfAppointment, hourOfAppointment, descriptionOfAppointment);
        appointment.assignedClient = client;

        //TODO - asocjacja kwalifikowana Klient(IdWizyty) * - 1 Wizyta PART 1
        client.addAppointment(appointment);
        return appointment;
    }
    //TODO - kompozycja v1 Wizyta 1 - * Usluga PART 1
    public void addMedicalService(MedicalService medicalService){
        if(!medicalServiceList.contains(medicalService)) {
            if(allMedicalServices.contains(medicalService)) {
                return; //tod exception
            }

            medicalServiceList.add(medicalService);
            allMedicalServices.add(medicalService);
        }
    }

    //tod metoda testowa do sprawdzen utrwalania ekstencji i podwojnej kompozycji
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

    public int getAppointmentID(){
        return this.appointmentID;
    }
//endregion Getters and Setters


}
