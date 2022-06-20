package com.mpfinal.mp_final.Model.System;

import com.mpfinal.mp_final.Model.Animals.*;
import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.ClinicServices.*;
import com.mpfinal.mp_final.Model.CustomModelExceptions.DoubleAssignmentException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.EmployeeRoleException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.ObjectNotFoundException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.OpeningHoursException;
import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.Internal.EmployeeRoles;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ExtensionManager implements Serializable {
    private static Map<Class, List<ExtensionManager>> allExtents = new Hashtable<>();

    private int id;
    public ExtensionManager() {
        id = IDGenerator.generateUniqueID();

        List extent = null;
        Class theClass = this.getClass();

        if(allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        }
        else {
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }

        extent.add(this);
    }

    public int getId(){
        return this.id;
    }

    public static boolean isExtendEmpty(){
        return allExtents.isEmpty();
    }

    /**
     * Saves entire extend map to provided directory.
     * @param outDirectory String
     */
    public static void writeExtents(String outDirectory) {
        try {
            FileOutputStream writeData = new FileOutputStream(outDirectory);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(allExtents);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Recreates extension map from provided directory.
     * If directory is invalid - call recreateBaseObjects.
     * @param inDirectory String
     * {@link #recreateBaseObjects()}
     */
    public static void readExtents(String inDirectory) {
        if (new File(inDirectory).exists()) {
            try {
                FileInputStream readData = new FileInputStream(inDirectory);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                allExtents = (Map<Class, List<ExtensionManager>>) readStream.readObject();
                readStream.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                recreateBaseObjects();
            }
        }else {
            recreateBaseObjects();
        }
        IDGenerator.setIdGeneratorAfterRecreatingExtension(allExtents);
    }

    /**
     * Returns all object of provided class type.
     * @param type Object.class
     * @param <T> T
     * @return T
     * @throws ClassNotFoundException when class does not extists
     */
    public static <T> ArrayList<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if(allExtents.containsKey(type)) {
            return (ArrayList<T>) allExtents.get(type);
        }

        throw new ClassNotFoundException(String.format("%s. Stored extents: %s", type.toString(), allExtents.keySet()));
    }

    /**
     * Seed data if no save file is avaible.
     */
    private static void recreateBaseObjects(){

        /*
        Struktura:
            * Pracownicy:
                - Recepcjonista 1 -
         */

        //make 5 example objects for each class
        List<Employee> receptionist = new ArrayList<>();
        List<Employee> vets = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        List<MedicalCard> medicalCards = new ArrayList<>();
        List<Appointment> appointments = new ArrayList<>();
        List<Medicine> medicineList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //make receptionist
            receptionist.add(new Employee("RecepName"+i,"RecepSurname"+i,
                    new Address("CityRec"+i,"StreetRec"+i,"HouseNumRec"+i, "ApartmentNumRec"+i)
                    , LocalDate.of(2022,i+1,i+1),true, EmployeeRoles.RECEPCIONIST));
            //make vet
            vets.add(new Employee("VetName"+i,"VetSurname"+i,
                    new Address("CityVet"+i,"StreetVet"+i,"HouseNumVet"+i, "ApartmentNumVet"+i)
                    , LocalDate.of(2022,i+1,i+1),true, EmployeeRoles.VET));

            //make Client
            clients.add(new Client("ClientName"+i,"ClientSurname"+i,
                    new Address("CityClient"+i,"StreetClient"+i,"HouseNumClient"+i, "ApartmentNumClient"+i),String.valueOf((111_111_111 * (i+1)))));

            //make Cats
            animals.add(i % 2 == 0?
                    new Cat("Cat"+i,"CatRace"+i,  "Chip"+i, true):
                    new Cat("Cat"+i,"CatRace"+i,  false));
            //make Dogs
            animals.add(i % 2 == 0?
                    new Dog("Dog"+i,"DogRace"+i,"Chip"+(i+5)):
                    new Dog("Dog"+i,"DogRace"+i));

            //make MedicalCards
            medicalCards.add(
                    new MedicalCard(LocalDate.now(), i+1)
            );

            //make Appointments
            try {
                appointments.add(
                        new Appointment(LocalDate.now(),(9+i), "Appointment"+i)
                );
            } catch (OpeningHoursException e) {
                e.printStackTrace();
            }
            //make Medicine
            medicineList.add(
                    new Medicine("Medicine"+i, (0.01f+i), (0.50f + i))
            );
        }
        //make base connections
        for (int i = 0; i < 5; i++) {
            //client-animal
            clients.get(i).addAnimal(animals.get(i));
            clients.get(i).addAnimal(animals.get(i+4));
            //animal-medicalCard
            animals.get(i).addMedicalCard(medicalCards.get(i));
            //appointment-ClientAnimalMedicalCard
            appointments.get(i).addClient(clients.get(i));
            try {
                appointments.get(i).addMedicalCard(clients.get(i).getClientAnimals().stream().filter(e -> {
                    try {
                        return e.getMedicalCard() != null;
                    } catch (ObjectNotFoundException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }).findFirst().get().getMedicalCard());
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
            //appontment-receptionist
            try {
                appointments.get(i).addReceptionist(receptionist.get(i));
            } catch (EmployeeRoleException e) {
                e.printStackTrace();
            } catch (DoubleAssignmentException e) {
                e.printStackTrace();
            }
            //appontment-vet
            try {
                appointments.get(i).addVet(vets.get(i));
            } catch (EmployeeRoleException e) {
                e.printStackTrace();
            } catch (DoubleAssignmentException e) {
                e.printStackTrace();
            }
            //appointment-medicalService
            try {
                appointments.get(i).addMedicalService(
                        MedicalService.createMedicalService(appointments.get(i), TypeOfMedicalService.INTERNAL_MEDICINE, "MedServ"+i, (100.99f + i) )
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            //new UsageOfMedicine
            new UsageOfMedicine(appointments.get(i).getMedicalServiceList().get(0),medicineList.get(i),1+i,"Reason"+1);
        }

        clients.add(
                new Client("AdditionalName","AdditionalSurname",
                        new Address("AddCity","AddStreet","AddHouseNum","addAptNum"),"123456789")
        );
        animals.add(new Cat("AddCat","AddRace",true));
        clients.get(clients.size()-1).addAnimal(animals.get(animals.size()-1));
        animals.get(animals.size()-1).addMedicalCard(new MedicalCard(LocalDate.now(),13));
    }
}
