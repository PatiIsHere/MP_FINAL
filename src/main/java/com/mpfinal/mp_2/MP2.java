package com.mpfinal.mp_2;


import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.CustomModelExceptions.DoubleAssignmentException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.EmployeeRoleException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.OpeningHoursException;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.Internal.EmployeeRoles;

import java.time.LocalDate;

public class MP2 {

    private static String objectSaveDir = System.getProperty("user.dir")+System.getProperty("file.separator")+"testExtensionFile.txt";

    public static void main(String[] args) {


        Address address = new Address("testad","testad","10");
        Employee recep = new Employee("a","b",address,LocalDate.of(2022,1,1),true, EmployeeRoles.RECEPCIONIST);
        Employee vet = new Employee("a","b",address,LocalDate.of(2022,1,1),true, EmployeeRoles.VET);
        vet.addRole(EmployeeRoles.RECEPCIONIST);
        recep.addRole(EmployeeRoles.VET);

        Appointment appointment = null;
        try {
            appointment = new Appointment(LocalDate.of(2022,1,1), 10, "esese");
        } catch (OpeningHoursException e) {
            e.printStackTrace();
        }
        try {
            appointment.addVet(vet);
        } catch (EmployeeRoleException e) {
            e.printStackTrace();
        } catch (DoubleAssignmentException e) {
            e.printStackTrace();
        }

        try {
            appointment.addReceptionist(recep);
        } catch (EmployeeRoleException e) {
            e.printStackTrace();
        } catch (DoubleAssignmentException e) {
            e.printStackTrace();
        }

        try {
            appointment.addVet(recep);
        } catch (EmployeeRoleException e) {
            e.printStackTrace();
        } catch (DoubleAssignmentException e) {
            e.printStackTrace();
        }

//        try {
//            appointment.addReceptionist(vet);
//        } catch (EmployeeRoleException e) {
//            e.printStackTrace();
//        } catch (DoubleAssignmentException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            appointment.addReceptionist(recep);
//        } catch (EmployeeRoleException e) {
//            e.printStackTrace();
//        } catch (DoubleAssignmentException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            appointment.addReceptionist(vet);
//        } catch (EmployeeRoleException e) {
//            e.printStackTrace();
//        } catch (DoubleAssignmentException e) {
//            e.printStackTrace();
//        }

//        Client client = new Client("a","b",new Address("testad","testad","10"),"0123");
//        Appointment appointment = new Appointment();
//
//        try {
//            client.getAppointmentById(1);
//        } catch (ObjectNotFoundException e) {
//            e.printStackTrace();
//        }


//        Client client = new Client("a","b",new Address("testad","testad","10"),"0123");
//        Client client1 = new Client("c","d",new Address("testad","testad","10"),"0123");
//        //Cat cat = new Cat("tst","test",client,"asc",true);
//       // Cat cat1 = new Cat("aaatst","aaatest",client,true);
//
//        MedicalCard medicalCard = new MedicalCard(LocalDate.now(),1);
//        //cat.addMedicalCard(medicalCard);
//
//       // cat.removeMedicalCard(medicalCard);
//
//       // medicalCard.addAnimal(cat1);
//
////        String c = null;
////        String b = "a";
////
////        System.out.println(b.equals(c));
////
////        Client client = new Client("a","b",new Address("testad","testad","10"),"0123");
////        Client client1 = new Client("c","d",new Address("testad","testad","10"),"0123");
////
////        Cat cat = new Cat("tst","test",client,"asc",true);
////        Cat cat1 = new Cat("aaatst","aaatest",client,true);
////
////        cat1.addClient(client1);
////
////        System.out.println(cat.getIDChip());
////        System.out.println(cat1.getIDChip());


//        //region AssociationWithAttribute
//        ExMedicalService exMedicalService = new ExMedicalService(TypeOfMedicalService.INTERNAL_MEDICINE,"asocjacja z atrybutem",11.20f);
//        ExMedicine exMedicine = new ExMedicine("Apap",0.05f,0.5f);
//        ExUsageOfMedicine exUsageOfMedicine = new ExUsageOfMedicine(exMedicalService,exMedicine,20,"bo testy");
//
//        System.out.println("BreakPoint na asocjacje z atrybutem");
//
//        //endregion AssociationWithAttribute
//
//
//        //read objects from txt file
//        try{
//            ExtensionManager.readExtents(objectSaveDir);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        //if file is empty - redo objects
//        if(ExtensionManager.isExtendEmpty()) {
//            try {
//                redoObjects();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        ExtensionManager.TESTOWO(); //stop for debug
//
//        ExtensionManager.writeExtents(objectSaveDir);


    }

    private static void redoObjects() throws Exception {


//        Employee vet = new Employee("VetTestName","VetTestSurname", LocalDate.of(2022,2,2),true,EmployeeRoles.VET);
//        Address address = new Address("Czosnow","Czosnkowa","10A");
//        Employee receptionist = new Employee("Kowal","Janowski", LocalDate.of(2022,2,2),true,EmployeeRoles.RECEPCIONIST);
//        Client client = new Client("KlientTestName", "KlientTestSurname",);
//
//        Appointment appointment = Appointment.createAppointment(receptionist,vet,client
//                ,LocalDate.of(2022,5,23),10,"Testowa wizyta");
//
//        appointment.addMedicalService(
//                MedicalService.createMedicalService(appointment, TypeOfMedicalService.INTERNAL_MEDICINE,"Testowa usluga",10.50f)
//        );
//
//        Medicine medicine = new Medicine("Ibuprofen",0.05f, 0.50f);
//
//        appointment.getMedSer().createUsageOfMedicineInMedicalService(medicine,2,"testowe podanie ibum");


    }
}
