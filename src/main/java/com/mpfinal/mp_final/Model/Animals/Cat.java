package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;

import java.io.Serializable;

public class Cat extends Animal implements Serializable {
    private boolean isOutgoing;

    /**
     * Constructor used when IDchip is provided
     * @param name String
     * @param race String
     * @param IDChip String
     * @param isOutgoing boolean
     */
    public Cat(String name, String race, String IDChip, boolean isOutgoing){
        super(name, race, IDChip);
        setOutgoing(isOutgoing);
    }

    /**
     * Base constructor
     * @param name String
     * @param race String
     * @param isOutgoing boolean
     */
    public Cat(String name, String race, boolean isOutgoing){
        super(name,race);
        this.isOutgoing = isOutgoing;
    }

    /**
     * Updates information if cat is outgoing.
     * @param isOutgoing boolean
     */
    public void setOutgoing(boolean isOutgoing){
            this.isOutgoing = isOutgoing;
    }
}
