package com.mpfinal.mp_final.Model.External;

import com.mpfinal.mp_final.Model.Animals.Animal;
import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.CustomModelExceptions.ContactInfoException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Client extends Person implements Serializable {

    private int clientID;
    private List<String> phoneNumbers = new ArrayList<>();

    private List<Animal> animals = new ArrayList<>();

    private Map<Integer, Appointment> appointments = new TreeMap<>();

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
     * Adds animal and creates connection between.
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
     * @param animal Animal
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
         * Adds appointment and creates connection between.
         * @param appointment Appointment
         */
        public void addAppointment(Appointment appointment){
            if(appointment != null) {
                if (appointments.isEmpty() || !appointments.containsKey(appointment.getId())) {
                    appointments.put(appointment.getId(), appointment);
                    appointment.addClient(this);
                }
            }
        }

        /**
         * Removes appointment and connection between.
         * @param appointment Appointment
         */
        public void removeAppointment(Appointment appointment){
            if(appointments.containsKey(appointment.getId())){
                appointments.remove(appointment.getId());
                appointment.removeClient(this);
            }

        }

        /**
         * Returns appointment with provided id.
         * @param idAppointment int
         * @return Appointment with given id.
         * @throws ObjectNotFoundException when no appointment found
         */
        public Appointment getAppointmentById(int idAppointment) throws ObjectNotFoundException {
            if(!this.appointments.containsKey(idAppointment)){
                throw new ObjectNotFoundException("No appointment under this id");
            }else {
                return this.appointments.get(idAppointment);
            }
        }

    //endregion Association Appointment

    public boolean isDateAndHourOccupied(LocalDate date, int hour){
            if (appointments.isEmpty()){
                return false;
            }else {
                return appointments.entrySet().stream()
                        .map(Map.Entry::getValue)
                        .filter(e -> e.getDateOfAppointment().equals(date))
                        .anyMatch(e -> e.getHourOfAppointment() == hour);
            }
    }


}
