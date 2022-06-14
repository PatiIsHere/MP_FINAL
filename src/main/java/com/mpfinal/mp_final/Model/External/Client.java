package com.mpfinal.mp_final.Model.External;

import com.mpfinal.mp_final.Model.Animals.Animal;
import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Client extends Person implements Serializable {

    private final int clientID;
    private List<String> phoneNumbers = new ArrayList<>();

    private List<Animal> animals = new ArrayList<>();

    private Map<Integer, Appointment> assignedAppointments = new TreeMap<>();

    public Client(String name, String surname, Address address, String phoneNumber ) {
        super(name, surname, address);
        addPhoneNumber(phoneNumber);
        clientID = IDGenerator.generateUniqueID();
    }

    //region Getters and Setters

    public int getClientID() {
        return clientID;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void addPhoneNumber(String phoneNumber){
        if(!phoneNumbers.contains(phoneNumber)){
            phoneNumbers.add(phoneNumber);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Map<Integer, Appointment> getAssignedAppointments() {
        return assignedAppointments;
    }

    //endregion Getters and Setters


    //region association animal
    public void addAnimal(Animal animal) {
        if(!animals.contains(animal)){
            animals.add(animal);
            animal.addClient(this);
        }
    }
    //endregion association Animal

    //region association Appointment
    public void addAppointment(Appointment appointment){
        if(appointment == null){
            return; // todo exception
        }
        if(assignedAppointments.isEmpty() || !assignedAppointments.containsKey(appointment.getAppointmentID())) {
            assignedAppointments.put(appointment.getAppointmentID(), appointment);
        }
    }




}
