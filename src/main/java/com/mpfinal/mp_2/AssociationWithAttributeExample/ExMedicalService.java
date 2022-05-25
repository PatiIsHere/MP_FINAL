package com.mpfinal.mp_2.AssociationWithAttributeExample;

import com.mpfinal.mp_final.Model.ClinicServices.TypeOfMedicalService;

import java.util.ArrayList;
import java.util.List;

public class ExMedicalService {

    private TypeOfMedicalService typeOfMedicalService;
    private String descriptionOfService;
    private float price;

    List<ExUsageOfMedicine> usageOfMedicines = new ArrayList<>();

    public ExMedicalService(TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        this.typeOfMedicalService = typeOfMedicalService;
        this.descriptionOfService = descriptionOfService;
        this.price = price;
    }

    public void addUsageOfMedicine(ExUsageOfMedicine usageOfMedicine){
        if(!usageOfMedicines.contains(usageOfMedicine)){
            usageOfMedicines.add(usageOfMedicine);
            usageOfMedicine.addMedicalService(this);
        }
    }


}
