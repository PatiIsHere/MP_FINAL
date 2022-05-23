package com.mpfinal.mp_final.Model.Animals;

import java.io.Serializable;
import java.util.List;

public class Dog extends Animal implements Serializable {

    private List<SpecialTraining> specialTrainingList;

    public Dog(String name, String race, String IDChip) {
        super(name, race, IDChip);
    }

    public void addSpecialTraining(SpecialTraining specialTraining) {
        if (this.specialTrainingList.contains(specialTraining)){
            return;
        } //todo throw exception czy nie rob nic?
        this.specialTrainingList.add(specialTraining);
    }
}
