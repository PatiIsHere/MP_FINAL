package com.mpfinal.mp_final.Model.External;

import com.mpfinal.mp_final.Model.Animals.Animal;
import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.CustomExceptions.ContactInfoException;
import com.mpfinal.mp_final.Model.CustomExceptions.ObjectNotFoundException;
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
    }

    //region Getters and Setters

    public int getClientID() {
        return clientID;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Adds new phone num to contact list if this num does not already exists.
     * @param phoneNumber
     */
    public void addPhoneNumber(String phoneNumber){
        if(!phoneNumbers.contains(phoneNumber)){
            phoneNumbers.add(phoneNumber);
        }
    }

    /**
     * Removes phone number from contact list.
     * @param phoneNumber
     * @throws ContactInfoException - at least one phone num must be assigned to client.
     */
    public void removePhoneNumber(String phoneNumber) throws ContactInfoException {
        if (this.phoneNumbers.size() == 1){
            throw new ContactInfoException("At least one phone number must be avaible!");
        }
        if(this.phoneNumbers.contains(phoneNumber)){
            phoneNumbers.remove(phoneNumber);
        }
    }

    /**
     *
     * @return list of Animals assigned to client
     */
    public List<Animal> getClientAnimals() {
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

    /**
     * Adds appointment and connection between.
     * @param appointment
     */
    public void addAppointment(Appointment appointment){
        if(appointment != null) {
            if (assignedAppointments.isEmpty() || !assignedAppointments.containsKey(appointment.getId())) {
                assignedAppointments.put(appointment.getId(), appointment);
            }
        }
    }

    /**
     *
     * @param idAppointment int
     * @return Appointment with given id.
     * @throws ObjectNotFoundException
     */
    public Appointment getAppointmentById(int idAppointment) throws ObjectNotFoundException {
        if(this.assignedAppointments.isEmpty() || !this.assignedAppointments.containsKey(idAppointment)){
            throw new ObjectNotFoundException("No appointment under this id");
        }else {
            return this.assignedAppointments.get(idAppointment);
        }
    }

    //endregion Association Appointment


}
