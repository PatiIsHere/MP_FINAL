package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicalService implements Serializable {
    private TypeOfMedicalService typeOfMedicalService;
    private String descriptionOfService;
    private float price;

    private List<MedicalServiceMedicine> medicineUsedInThisMedicalService = new ArrayList<>();
    private Set<MedicalServiceMedicine> allMedicineUsedInMedicalService = new HashSet<>();

    //tutaj w konstruktorze będzie wymagane, by podać appointment, inaczej nie zrobi obiektu (kompozycja)
    private MedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        this.typeOfMedicalService = typeOfMedicalService;
        this.descriptionOfService = descriptionOfService;
        this.price = price;
    }

    public static MedicalService createMedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        if(appointment == null){
            return null; //todo exception
        }

        MedicalService medicalService = new MedicalService(appointment, typeOfMedicalService, descriptionOfService, price);
        return medicalService;
    }

    public void addMedicalServiceMedicine(MedicalServiceMedicine medicalServiceMedicine) throws Exception {
        if(!medicineUsedInThisMedicalService.contains(medicalServiceMedicine)) {
            // Check if the part has been already added to any wholes
            if(allMedicineUsedInMedicalService.contains(medicalServiceMedicine)) {
                throw new Exception("The part is already connected with a whole!");
            }

            medicineUsedInThisMedicalService.add(medicalServiceMedicine);

            // Store on the list of all parts
            allMedicineUsedInMedicalService.add(medicalServiceMedicine);
        }
    }

    public MedicalServiceMedicine createUsageOfMedicineInMedicalService(MedicalService medicalService, Medicine medicine, int numOfDosages, String reasonForAdministeringMedicine){
        if(medicalService == null){
            return null; //todo exception
        }

        MedicalServiceMedicine medicalServiceMedicine = new MedicalServiceMedicine(medicine, numOfDosages, reasonForAdministeringMedicine);
        medicineUsedInThisMedicalService.add(medicalServiceMedicine);
        allMedicineUsedInMedicalService.add(medicalServiceMedicine);

        return medicalServiceMedicine;
    }

    public class MedicalServiceMedicine extends ExtensionManager implements Serializable{
        private Medicine medicine;
        private int numOfDosages;
        private String reasonForAdministeringMedicine;

        private MedicalServiceMedicine(Medicine medicine, int numOfDosages, String reasonForAdministeringMedicine) {
            super();
            this.medicine = medicine;
            this.numOfDosages = numOfDosages;
            this.reasonForAdministeringMedicine = reasonForAdministeringMedicine;
        }

        public MedicalService getMedicalService(){
            return MedicalService.this;
        }


    }
}
