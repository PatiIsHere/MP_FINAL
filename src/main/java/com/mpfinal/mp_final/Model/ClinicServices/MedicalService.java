package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class MedicalService {

    private Appointment appointment;
    private TypeOfMedicalService typeOfMedicalService;
    private String descriptionOfService;
    private float price;

    private int id;

    List<UsageOfMedicine> usageOfMedicines = new ArrayList<>();

    /**
     * Private constructor.
     * @param appointment
     * @param typeOfMedicalService
     * @param descriptionOfService
     * @param price
     */
    private MedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService, String descriptionOfService, float price) {
        this.appointment = appointment;
        this.typeOfMedicalService = typeOfMedicalService;
        this.descriptionOfService = descriptionOfService;
        setPrice(price);
        id = IDGenerator.generateUniqueID();
    }

    /**
     * Creates a new medical service and connects it with provided appointment.
     * @param appointment
     * @param typeOfMedicalService
     * @param descriptionOfService String
     * @param price float
     * @return MedicalService
     * @throws Exception
     */
    public MedicalService createMedicalService(Appointment appointment, TypeOfMedicalService typeOfMedicalService
            , String descriptionOfService, float price) throws Exception {
        if(appointment == null){
            throw new Exception("Medical service can be created only when appointment is provided!");
        }

        MedicalService medicalService = new MedicalService(appointment, typeOfMedicalService, descriptionOfService, price);
        appointment.addMedicalService(medicalService);
        return medicalService;
    }

    //region Getters and Setters
        public Appointment getAppointment() {
            return appointment;
        }

        public TypeOfMedicalService getTypeOfMedicalService() {
            return typeOfMedicalService;
        }

        /**
         * Updates the type of medical service
         * @param typeOfMedicalService
         */
        public void setTypeOfMedicalService(TypeOfMedicalService typeOfMedicalService) {
            if(this.typeOfMedicalService != typeOfMedicalService) {
                this.typeOfMedicalService = typeOfMedicalService;
            }
        }

        public String getDescriptionOfService() {
            return descriptionOfService;
        }

        /**
         * Updates description of medical service
         * @param descriptionOfService
         */
        public void setDescriptionOfService(String descriptionOfService) {
            this.descriptionOfService = descriptionOfService;
        }

        /**
         * Sets price of medical service.
         * @param price
         * @throws IllegalArgumentException
         */
        public void setPrice(float price) throws IllegalArgumentException {
            if (price < 0) {
                this.price = price;
            }else {
                throw new IllegalArgumentException("Price cannot be negative!");
            }
        }

        /**
         *
         * @return base price of medical service
         */
        public float getPrice() {
            return price;
        }

        /**
         *
         * @return final price of medical service with calculated price for medicine
         */
        public float getPriceWithUsageOfMedicine(){
            float finalPrice = price;

            if (usageOfMedicines.size() == 0){
                return finalPrice;
            }else {
                for (UsageOfMedicine usageOfMedicine : usageOfMedicines) {
                    int dosages = usageOfMedicine.getNumOfDosages();
                    float pricePerDosage = usageOfMedicine.getMedicine().getPricePerDosage();
                    finalPrice += (pricePerDosage * dosages);
                }
            }
            return finalPrice;
        }

        public List<UsageOfMedicine> getUsageOfMedicines() {
            return usageOfMedicines;
        }

        public int getId() {
            return this.id;
        }

    //endregion Getters and Setters

    //region Association UsageOfMedicine

        /**
         * Adds usage of medicine and create connection between.
         * @param usageOfMedicine
         */
        public void addUsageOfMedicine(UsageOfMedicine usageOfMedicine){
            if(usageOfMedicine != null && !usageOfMedicines.contains(usageOfMedicine)){
                usageOfMedicines.add(usageOfMedicine);
                usageOfMedicine.addMedicalService(this);
            }
        }

        /**
         * Removes usage of medicine and connection between.
         * @param usageOfMedicine
         */
        public void removeUsageOfMedicine(UsageOfMedicine usageOfMedicine) {
            if(usageOfMedicines.contains(usageOfMedicine)){
                usageOfMedicines.remove(usageOfMedicine);
                usageOfMedicine.removeMedicalService(this);
            }
        }


    //endregion Association UsageOfMedicine


}
