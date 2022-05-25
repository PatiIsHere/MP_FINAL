package com.mpfinal.mp_2.AssociationWithAttributeExample;

public class ExUsageOfMedicine {

    private int numOfDosages;
    private String reasonForAdministeringMedicine;

    private ExMedicalService medicalService;
    private ExMedicine medicine;

    public ExUsageOfMedicine(ExMedicalService medicalService, ExMedicine medicine, int numOfDosages, String reasonForAdministeringMedicine){
        this.numOfDosages = numOfDosages;
        this.reasonForAdministeringMedicine = reasonForAdministeringMedicine;
        addMedicalService(medicalService);
        addMedicine(medicine);
    }
    //TODO poprawka [Poprawić z atrybutem (liczności w klasie atrybutu)] 1/2
    public void addMedicalService(ExMedicalService medicalService){
        if(this.medicalService == null && medicalService != null){
            this.medicalService = medicalService;
            medicalService.addUsageOfMedicine(this);
        }
    }
    //TODO poprawka [Poprawić z atrybutem (liczności w klasie atrybutu)] 2/2
    public void addMedicine(ExMedicine medicine){
        if(this.medicine == null && medicine != null){
            this.medicine = medicine;
            medicine.addUsageOfMedicine(this);
        }
    }





}
