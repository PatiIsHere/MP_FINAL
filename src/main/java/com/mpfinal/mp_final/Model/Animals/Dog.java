package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dog extends Animal implements Serializable {

    private Set<SpecialTraining> specialTrainingList = new HashSet<>();

    /**
     * Constructor used when IDchip is provided
     * @param name
     * @param race
     * @param IDChip
     */
    public Dog(String name, String race, String IDChip) {
        super(name, race, IDChip);
    }

    /**
     * Base constructor
     * @param name
     * @param race
     */
    public Dog(String name, String race) {
        super(name, race);
    }

    /**
     * Adds special training.
     * @param specialTraining
     */
    public void addSpecialTraining(SpecialTraining specialTraining) {
        if (!this.specialTrainingList.contains(specialTraining)) {
            this.specialTrainingList.add(specialTraining);
        }
    }
}
