package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.System.ExtensionManager;
import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.io.Serializable;
import java.util.List;

public abstract class Animal extends ExtensionManager implements Serializable {
    private String name;
    private String race;
    private String IDChip;
    private final int IDAnimal;

    public Animal(String name, String race) {
        super();
        this.name = name;
        this.race = race;
        this.IDChip = null; //todo check if id exist and if yes - throw exception
        IDAnimal = IDGenerator.generateUniqueID();
    }
    public Animal(String name, String race, String IDChip) {
        super();
        this.name = name;
        this.race = race;
        this.IDChip = IDChip; //todo check if id exist and if yes - throw exception
        IDAnimal = IDGenerator.generateUniqueID();
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

    public void setIDChip(String IDChip) {
        this.IDChip = IDChip;
    }
//endregion Getters and Setters

    public static Animal getAnimalByChipId(List<Animal> animalList, String IDChip){
        //tod implementacja
        return null;
    }

}
