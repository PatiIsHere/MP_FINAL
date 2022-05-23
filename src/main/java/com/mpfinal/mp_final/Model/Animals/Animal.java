package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;
import java.util.List;

public abstract class Animal extends ExtensionManager implements Serializable {
    private String name;
    private String race;
    private String IDChip;

    public Animal(){

    }

    public Animal(String name, String race, String IDChip) {
        super();
        this.name = name;
        this.race = race;
        this.IDChip = IDChip;
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
        //todo implementacja
        return null;
    }

}
