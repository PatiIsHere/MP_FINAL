package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.io.Serializable;
import java.util.List;

public abstract class Animal extends ExtensionManager implements Serializable {
    private final int IDAnimal;
    private String name;
    private String race;
    private String IDChip;
    private Client client;

    public Animal(String name, String race, Client client, String IDChip) throws Exception {
        super();
        this.name = name;
        this.race = race;
        this.client = client;
        setIDChip(IDChip);
        IDAnimal = IDGenerator.generateUniqueID();
    }

    public Animal(String name, String race, Client client) throws Exception {
        this(name, race, client, null);
    }

    //region Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getIDChip() {
            return IDChip;
        }

        /**
         * Veryfies if provided IDChip exist in Animal extension.
         * If it's unique - assign value to IDChip
         * @param IDChip
         * @throws Exception when the IDChip already exists.
         */
        public void setIDChip(String IDChip) throws Exception {
            try {
                boolean IDChipAlreadyExists = this.getExtent(Animal.class)
                                                .stream()
                                                .anyMatch(e -> e.getIDChip().equals(IDChip));
                if (IDChipAlreadyExists) {
                    throw new Exception("This IDChip already exist!");
                }else {
                    this.IDChip = IDChip;
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    //endregion Getters and Setters

    //region association Customer

    //endregion association Customer

    public static Animal getAnimalByChipId(List<Animal> animalList, String IDChip){
        //tod implementacja
        return null;
    }
    public Animal getAnimalByChipID(String idAnimalSearch){
        Animal searchedAnimal = null;
        try
        {
            searchedAnimal = this.getExtent(Animal.class)
                    .stream()
                    .filter(e -> e.getIDChip().equals(idAnimalSearch))
                    .findFirst().get();
        }
        catch (ClassNotFoundException e)
        {
        e.printStackTrace();
        }
        return searchedAnimal;
    }

    public void addClient(Client client){

    }
}
