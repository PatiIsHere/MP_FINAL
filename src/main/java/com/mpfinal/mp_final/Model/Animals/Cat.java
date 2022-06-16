package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;

import java.io.Serializable;

public class Cat extends Animal implements Serializable {
    private boolean isOutgoing;

    /**
     *
     * @param name
     * @param race
     * @param client
     * @param IDChip
     * @param isOutgoing
     */
    public Cat(String name, String race, Client client, String IDChip, boolean isOutgoing){
        super(name, race, client, IDChip);
        this.isOutgoing = isOutgoing;
    }
    public Cat(String name, String race, Client client, boolean isOutgoing){
        super(name,race,client);
        this.isOutgoing = isOutgoing;
    }
}
