package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.util.ArrayList;
import java.util.List;

public class Medicine extends ExtensionManager {
    private String name;
    private float dosage;
    private float pricePerDosage;

    private List<UsageOfMedicine> usageOfMedicines = new ArrayList<>();

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
            if(dosage < 0){
                throw new IllegalArgumentException("Dosage cannot be negative!");
            }
            this.dosage = dosage;
        }

        public float getPricePerDosage() {
            return pricePerDosage;
        }

        public void setPricePerDosage(float pricePerDosage) {
            if (pricePerDosage < 0){
                throw new IllegalArgumentException("Price cannot be negative!");
            }
            this.pricePerDosage = pricePerDosage;
        }
    //endregion Getters and Setters

    //region Association UsageOfMedicine
        public void addUsageOfMedicine(UsageOfMedicine usageOfMedicine) {
            if(usageOfMedicine != null && !usageOfMedicines.contains(usageOfMedicine)){
                usageOfMedicines.add(usageOfMedicine);
                usageOfMedicine.addMedicine(this);
            }
        }

        public void removeUsageOfMedicine(UsageOfMedicine usageOfMedicine) {
            if(usageOfMedicines.contains(usageOfMedicine)){
                usageOfMedicines.remove(usageOfMedicine);
                usageOfMedicine.removeMedicine(this);
            }
        }
    //endregion Association UsageOfMedicine
}
