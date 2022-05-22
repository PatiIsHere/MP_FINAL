package com.mpfinal.mp_final.Model.Animals;

public class Cat extends Animal{
    private boolean isOutgoing;

    public Cat(){}

    public Cat(String name, String race, String IDChip, boolean isOutgoing) {
        super(name, race, IDChip);
        this.isOutgoing = isOutgoing;
    }
}
