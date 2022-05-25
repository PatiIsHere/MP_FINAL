package com.mpfinal.mp_2;


import com.mpfinal.mp_2.AssociationWithAttributeExample.ExMedicalService;
import com.mpfinal.mp_2.AssociationWithAttributeExample.ExMedicine;
import com.mpfinal.mp_2.AssociationWithAttributeExample.ExUsageOfMedicine;
import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.ClinicServices.MedicalService;
import com.mpfinal.mp_final.Model.ClinicServices.Medicine;
import com.mpfinal.mp_final.Model.ClinicServices.TypeOfMedicalService;
import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.Internal.ContractType;
import com.mpfinal.mp_final.Model.Internal.Receptionist;
import com.mpfinal.mp_final.Model.Internal.Vet;
import com.mpfinal.mp_final.Model.OneAboveAll.ExtensionManager;

import java.time.LocalDate;

public class MP2 {

    private static String objectSaveDir = System.getProperty("user.dir")+System.getProperty("file.separator")+"testExtensionFile.txt";

    public static void main(String[] args) {

        //region AssociationWithAttribute
        ExMedicalService exMedicalService = new ExMedicalService(TypeOfMedicalService.INTERNAL_MEDICINE,"asocjacja z atrybutem",11.20f);
        ExMedicine exMedicine = new ExMedicine("Apap",0.05f,0.5f);
        ExUsageOfMedicine exUsageOfMedicine = new ExUsageOfMedicine(exMedicalService,exMedicine,20,"bo testy");

        System.out.println("BreakPoint na asocjacje z atrybutem");

        //endregion AssociationWithAttribute


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

        ExtensionManager.TESTOWO(); //stop for debug

        ExtensionManager.writeExtents(objectSaveDir);


    }

    private static void redoObjects() throws Exception {


        Vet vet = new Vet("VetTestName","VetTestSurname", LocalDate.of(2022,2,2),null,true);
        Address address = new Address("Czosnow","Czosnkowa","10A");
        Receptionist receptionist = new Receptionist("Kowal","Janowski", LocalDate.of(2022,2,2),null,true, ContractType.FULL_TIME);
        Client client = new Client("KlientTestName", "KlientTestSurname");

        Appointment appointment = Appointment.createAppointment(receptionist,vet,client
                ,LocalDate.of(2022,5,23),10,"Testowa wizyta");

        appointment.addMedicalService(
                MedicalService.createMedicalService(appointment, TypeOfMedicalService.INTERNAL_MEDICINE,"Testowa usluga",10.50f)
        );

        Medicine medicine = new Medicine("Ibuprofen",0.05f, 0.50f);

        appointment.getMedSer().createUsageOfMedicineInMedicalService(medicine,2,"testowe podanie ibum");


    }
}
