package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dog extends Animal implements Serializable {

    private Set<SpecialTraining> specialTrainingList = new HashSet<>();

    public Dog(String name, String race, Client client) {
        super(name, race, client);
    }

    public Dog(String name, String race, Client client, String IDChip) {
        super(name, race, client, IDChip);
    }

    public void addSpecialTraining(SpecialTraining specialTraining) {
        if (this.specialTrainingList.contains(specialTraining)){
            return;
        } //tod throw exception czy nie rob nic?
        this.specialTrainingList.add(specialTraining);
    }
}
