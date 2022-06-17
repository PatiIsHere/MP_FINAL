package com.mpfinal.mp_final.Model.Animals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dog extends Animal implements Serializable {

    private Set<SpecialTraining> specialTrainingList = new HashSet<>();

    /**
     * Constructor used when IDchip is provided
     * @param name String
     * @param race String
     * @param IDChip String
     */
    public Dog(String name, String race, String IDChip) {
        super(name, race, IDChip);
    }

    /**
     * Base constructor
     * @param name String
     * @param race String
     */
    public Dog(String name, String race) {
        super(name, race);
    }

    /**
     * Adds special training.
     * @param specialTraining SpecialTraining
     */
    public void addSpecialTraining(SpecialTraining specialTraining) {
        if (!this.specialTrainingList.contains(specialTraining)) {
            this.specialTrainingList.add(specialTraining);
        }
    }

    /**
     * Removes special training.
     * @param specialTraining SpecialTraining
     */
    public void removeSpecialTraining(SpecialTraining specialTraining) {
        if (this.specialTrainingList.contains(specialTraining)) {
            this.specialTrainingList.remove(specialTraining);
        }
    }
}
