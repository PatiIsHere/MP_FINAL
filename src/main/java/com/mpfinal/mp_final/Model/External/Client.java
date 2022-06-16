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

    private int clientID;
    private List<String> phoneNumbers = new ArrayList<>();

    private List<Animal> animals = new ArrayList<>();

    private Map<Integer, Appointment> assignedAppointments = new TreeMap<>();

    /**
     * Constructor
     * @param name
     * @param surname
     * @param address
     * @param phoneNumber
     */
    public Client(String name, String surname, Address address, String phoneNumber ) {
        super(name, surname, address);
        addPhoneNumber(phoneNumber);
        clientID = IDGenerator.generateUniqueID();
    }

    //region Static methods

    //endregion Static methods

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



    //endregion Getters and Setters

    //region Association Animal

    /**
     * Adds animal and create connection between.
     * @param animal
     */
    public void addAnimal(Animal animal) {
        if(!animals.contains(animal)){
            animals.add(animal);
            animal.addClient(this);
        }
    }
    /**
     * Removes animal and connection between.
     * @param animal
     */
    public void removeAnimal(Animal animal){
        if(animals.contains(animal)){
            animals.remove(animal);
            animal.removeClient(this);
        }
    }
    //endregion Association Animal

    //region Association Appointment
    public void addAppointment(Appointment appointment){
        if(appointment == null){
            return; // todo exception
        }
        if(assignedAppointments.isEmpty() || !assignedAppointments.containsKey(appointment.getAppointmentID())) {
            assignedAppointments.put(appointment.getAppointmentID(), appointment);
        }
    }

    public Map<Integer, Appointment> getAssignedAppointments() {
        return assignedAppointments;
    }

    //endregion Association Appointment


}
