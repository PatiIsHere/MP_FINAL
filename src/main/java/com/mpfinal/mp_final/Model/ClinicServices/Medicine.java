package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;

public class Medicine extends ExtensionManager implements Serializable {

    private String name;
    private float dosage;
    private float pricePerDosage;

    public Medicine(String name, float dosage, float pricePerDosage) {
        super();
        this.name = name;
        this.dosage = dosage;
        this.pricePerDosage = pricePerDosage;
    }

//region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public float getPricePerDosage() {
        return pricePerDosage;
    }

    public void setPricePerDosage(float pricePerDosage) {
        this.pricePerDosage = pricePerDosage;
    }
//endregion Getters and Setters

}
