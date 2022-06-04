package com.mpfinal.mp_final.Model.ClinicServices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicalService implements Serializable {
    private Appointment appointment;
    private TypeOfMedicalService typeOfMedicalService;
    private String descriptionOfService;
    private float price;

    private List<MedicalServiceMedicine> medicineUsedInMedicalService = new ArrayList<>();
    private static Set<MedicalServiceMedicine> allMedicineUsedInMedicalService = new HashSet<>();

    //tutaj w konstruktorze będzie wymagane, by podać appointment, inaczej nie zrobi obiektu (kompozycja)
    private MedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        this.appointment = appointment;
        this.typeOfMedicalService = typeOfMedicalService;
        this.descriptionOfService = descriptionOfService;
        this.price = price;
        appointment.addMedicalService(this);
    }


    public static MedicalService createMedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        if(appointment == null){
            return null; //todo exception
        }

        return new MedicalService(appointment, typeOfMedicalService, descriptionOfService, price);
    }

    public MedicalServiceMedicine createUsageOfMedicineInMedicalService(Medicine medicine, int numOfDosages, String reasonForAdministeringMedicine){

        MedicalServiceMedicine medicalServiceMedicine = new MedicalServiceMedicine(medicine, numOfDosages, reasonForAdministeringMedicine);
        medicineUsedInMedicalService.add(medicalServiceMedicine);
        allMedicineUsedInMedicalService.add(medicalServiceMedicine);

        return medicalServiceMedicine;
    }

    public class MedicalServiceMedicine implements Serializable{
        private Medicine medicine;
        private int numOfDosages;
        private String reasonForAdministeringMedicine;

        private MedicalServiceMedicine(Medicine medicine, int numOfDosages, String reasonForAdministeringMedicine) {
            this.medicine = medicine;
            this.numOfDosages = numOfDosages;
            this.reasonForAdministeringMedicine = reasonForAdministeringMedicine;
        }

        public MedicalService getMedicalService(){
            return MedicalService.this;
        }


    }
}
