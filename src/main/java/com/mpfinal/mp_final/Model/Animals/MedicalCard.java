package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.CustomModelExceptions.ObjectNotFoundException;
import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalCard extends ExtensionManager implements Serializable {

    private LocalDate registrationDate;
    private int age;
    private Animal animal;

    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Constructor.
     * @param registrationDate LocalDate
     * @param age int
     */
    public MedicalCard(LocalDate registrationDate, int age) {
        super();
        this.registrationDate = registrationDate;
        setAge(age);
    }

    //region Getters and Setters


        public LocalDate getRegistrationDate() {
            return registrationDate;
        }

        /**
         * Sets the registration date.
         * @param registrationDate LocalDate
         */
        public void setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
        }

        public int getAge() {
                return age;
            }

        /**
         * Sets the age - cannot be negative.
         * @param age int
         */
        public void setAge(int age) {
            if(age >= 0) {
                this.age = age;
            }
        }

        /**
         * Returns assigned animal.
         * @return Animal
         * @throws ObjectNotFoundException when animal is not assigned
         */
        public Animal getAnimal() throws ObjectNotFoundException {
            if(animal != null){
                return animal;
            }else {
                throw new ObjectNotFoundException("No animal assigned to this medical card!");
            }
        }

    //endregion Getters and Setters

    //region Association Animal

        /**
         * Adds animal and connection between.
         * @param animal Animal
         */
        public void addAnimal(Animal animal) {
        if (this.animal == null && animal != null){
            this.animal = animal;
            animal.addMedicalCard(this);
        }
        }

        /**
         * Removes animal and connection between.
         * @param animal Animal
         */
        public void removeAnimal(Animal animal) {
        if(animal != null
                && this.animal != null
                && this.animal.getId() == animal.getId()){
            this.animal = null;
            animal.removeMedicalCard(this);
        }
        }
    //endregion Association Animal

    //region Association Appointment

    /**
     * Adds appointment and create connection between.
     * @param appointment Appointment
     */
    public void addAppointment(Appointment appointment) {
        if(!appointments.contains(appointment)){
            appointments.add(appointment);
            appointment.addMedicalCard(this);
        }
    }
    /**
     * Removes appointment and connection between.
     * @param appointment Appointment
     */
    public void removeAppointment(Appointment appointment){
        if(appointments.contains(appointment)){
            appointments.remove(appointment);
            appointment.removeMedicalCard(this);
        }
    }


    //endregion Association Appointment


}
