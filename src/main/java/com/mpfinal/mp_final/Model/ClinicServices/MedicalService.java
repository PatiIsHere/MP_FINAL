package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;

public class MedicalService extends ExtensionManager implements Serializable {
    private TypeOfMedicalService typeOfMedicalService;
    private String descriptionOfService;
    private float price;

    //tutaj w konstruktorze będzie wymagane, by podać appointment, inaczej nie zrobi obiektu (kompozycja)
    public MedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        super();
        this.typeOfMedicalService = typeOfMedicalService;
        this.descriptionOfService = descriptionOfService;
        this.price = price;
    }

    public class MedicalServiceMedicine extends ExtensionManager implements Serializable{
        private Medicine medicine;
        private int numOfDosages;
        private String reasonForAdministeringMedicine;

        public MedicalServiceMedicine(Medicine medicine, int numOfDosages, String reasonForAdministeringMedicine) {
            super();
            this.medicine = medicine;
            this.numOfDosages = numOfDosages;
            this.reasonForAdministeringMedicine = reasonForAdministeringMedicine;
        }
    }
}
