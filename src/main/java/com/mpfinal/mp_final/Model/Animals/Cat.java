package com.mpfinal.mp_final.Model.Animals;

import java.io.Serializable;

public class Cat extends Animal implements Serializable {
    private boolean isOutgoing;

    public Cat(String name, String race, String IDChip, boolean isOutgoing) {
        super(name, race, IDChip);
        this.isOutgoing = isOutgoing;
    }
}
