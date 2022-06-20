package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;

public class UsageOfMedicine extends ExtensionManager implements Serializable {

    private int numOfDosages;
    private String reasonForAdministeringMedicine;

    private MedicalService medicalService;
    private Medicine medicine;

    public UsageOfMedicine(MedicalService medicalService, Medicine medicine, int numOfDosages, String reasonForAdministeringMedicine){
        super();
        setNumOfDosages(numOfDosages);
        setReasonForAdministeringMedicine(reasonForAdministeringMedicine);
        addMedicalService(medicalService);
        addMedicine(medicine);
    }

    //region Getters and Setters
        public int getNumOfDosages() {
            return numOfDosages;
        }

        public MedicalService getMedicalService() {
            return medicalService;
        }

        public Medicine getMedicine() {
            return medicine;
        }

        /**
         * Sets the num of doses used when value > 0.
         * @param numOfDosages int
         */
        public void setNumOfDosages(int numOfDosages) {
            if(numOfDosages < 0) {
                throw new IllegalArgumentException("Number of dosage cannot be negative!");
            }
            this.numOfDosages = numOfDosages;
        }

        public String getReasonForAdministeringMedicine() {
            return reasonForAdministeringMedicine;
        }

        /**
         * Sets the reason for administering medicine.
         * @param reasonForAdministeringMedicine String
         */
        public void setReasonForAdministeringMedicine(String reasonForAdministeringMedicine) {
            if (reasonForAdministeringMedicine == null || reasonForAdministeringMedicine.length() == 0){
                throw new IllegalArgumentException("Description cannot be empty");
            }
            this.reasonForAdministeringMedicine = reasonForAdministeringMedicine;
        }
    //endregion Getters and Setters

    //region Association MedicalService
        /**
         * Adds medical service and creates connection between.
         * @param medicalService MedicalService
         */
        public void addMedicalService(MedicalService medicalService){
            if(this.medicalService == null && medicalService != null){
                this.medicalService = medicalService;
                medicalService.addUsageOfMedicine(this);
            }
        }

        /**
         * Removes medical service and connection between.
         * @param medicalService MedicalService
         */
        public void removeMedicalService(MedicalService medicalService){
            if(this.medicalService != null && medicalService != null && this.medicalService.getId() == medicalService.getId()){
                this.medicalService = null;
                medicalService.removeUsageOfMedicine(this);
            }
        }
    //endregion Association MedicalService

    //region Association Medicine
        /**
         * Adds medicine and creates connection between.
         * @param medicine Medicine
         */
        public void addMedicine(Medicine medicine){
            if(this.medicine == null && medicine != null){
                this.medicine = medicine;
                medicine.addUsageOfMedicine(this);
            }
        }

        /**
         * Removes medicine and connection between.
         * @param medicine Medicine
         */
        public void removeMedicine(Medicine medicine){
            if(this.medicine != null && medicine != null && this.medicine.getId() == medicine.getId()) {
                this.medicine = null;
                medicine.removeUsageOfMedicine(this);
            }
        }

    //endregion Association Medicine
}
