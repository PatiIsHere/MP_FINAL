package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;

import java.io.Serializable;

public class Cat extends Animal implements Serializable {
    private boolean isOutgoing;

    /**
     * Constructor used when IDchip is provided
     * @param name
     * @param race
     * @param IDChip
     * @param isOutgoing
     */
    public Cat(String name, String race, Client client, String IDChip, boolean isOutgoing){
        super(name, race, IDChip);
        this.isOutgoing = isOutgoing;
    }

    /**
     * Base constructor
     * @param name
     * @param race
     * @param client
     * @param isOutgoing
     */
    public Cat(String name, String race, Client client, boolean isOutgoing){
        super(name,race);
        this.isOutgoing = isOutgoing;
    }

    /**
     * Updates information if cat is outgoing.
     * @param isOutgoing
     */
    public void setOutgoing(boolean isOutgoing){
        if (this.isOutgoing != isOutgoing){
            this.isOutgoing = isOutgoing;
        }
    }
}
