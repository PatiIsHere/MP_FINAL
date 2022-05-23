package com.mpfinal.mp_2;


import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.ClinicServices.MedicalService;
import com.mpfinal.mp_final.Model.ClinicServices.Medicine;
import com.mpfinal.mp_final.Model.ClinicServices.TypeOfMedicalService;
import com.mpfinal.mp_final.Model.Internal.Vet;
import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.time.LocalDate;

public class MP2 {

    private static String objectSaveDir = System.getProperty("user.dir")+System.getProperty("file.separator")+"testExtensionFile.txt";

    public static void main(String[] args) {

//        try {
//            redoObjects();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //read objects from txt file
        try{
            ExtensionManager.readExtents(objectSaveDir);
        }catch (Exception e){
            e.printStackTrace();
        }

        //if file is empty - redo objects
        if(ExtensionManager.isExtendEmpty()) {
            try {
                redoObjects();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ExtensionManager.TESTOWO();

        ExtensionManager.writeExtents(objectSaveDir);


    }

    private static void redoObjects() throws Exception {

        Vet vet = new Vet("Jan","Kowalski", LocalDate.of(2022,2,2),null,true);
        Address address = new Address("Czosnow","Czosnkowa","10A");

        Appointment appointment = new Appointment(LocalDate.of(2022,5,23),10,"Testowa wizyta");
        appointment.addMedicalService(
                MedicalService.createMedicalService(appointment, TypeOfMedicalService.INTERNAL_MEDICINE,"Testowa usluga",10.50f)
        );

        Medicine medicine = new Medicine("Ibuprofen",0.05f, 0.50f);
        //coś tutaj nie działa zapisywanie usluga_lek do arraylisty i setu
        appointment.getMedSer().createUsageOfMedicineInMedicalService(appointment.getMedSer(),medicine,2,"testowe podanie ibum");


    }
}
