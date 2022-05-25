package com.mpfinal.mp_2.AssociationWithAttributeExample;

import java.util.ArrayList;
import java.util.List;

public class ExMedicine {
    private String name;
    private float dosage;
    private float pricePerDosage;

    private List<ExUsageOfMedicine> usageOfMedicines = new ArrayList<>();

    public ExMedicine(String name, float dosage, float pricePerDosage) {
        this.name = name;
        this.dosage = dosage;
        this.pricePerDosage = pricePerDosage;
    }

    public void addUsageOfMedicine(ExUsageOfMedicine usageOfMedicine) {
        if(!usageOfMedicines.contains(usageOfMedicine)){
            usageOfMedicines.add(usageOfMedicine);
            usageOfMedicine.addMedicine(this);
        }
    }
}
