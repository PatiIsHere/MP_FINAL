package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.CustomExceptions.ObjectNotFoundException;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicalCard extends ExtensionManager implements Serializable {
    private int IDMedicalCard;
    private LocalDate registrationDate;
    private int age;
    private Animal animal;

    public MedicalCard(LocalDate registrationDate, int age) {
        super();
        IDMedicalCard = IDGenerator.generateUniqueID();
        this.registrationDate = registrationDate;
        setAge(age);
    }

    //region Getters and Setters

        public int getIDMedicalCard() {
            return IDMedicalCard;
        }

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
            if(age > 0) {
                this.age = age;
            }
        }

        /**
         * Returns assigned animal.
         * @return
         * @throws ObjectNotFoundException
         */
        public Animal getAnimal() throws ObjectNotFoundException {
            if(animal != null){
                return animal;
            }else {
                throw new ObjectNotFoundException("No animal assgined to this medical card!");
            }
        }

    //endregion Getters and Setters

    //region Association Animal

        /**
         * Adds animal and connection between.
         * @param animal
         */
        public void addAnimal(Animal animal) {
        if (this.animal == null && animal != null){
            this.animal = animal;
            animal.addMedicalCard(this);
        }
        }

        /**
         * Removes animal and connection between.
         * @param animal
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


}
