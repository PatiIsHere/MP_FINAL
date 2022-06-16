package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.CustomExceptions.ObjectAlreadyInUseException;
import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Appointment extends ExtensionManager implements Serializable {
    private static final int OPENING_HOUR = 8;
    private static final int CLOSING_HOUR = 21;
    private static final float MINIMAL_PRICE = 49.99f;

    private LocalDate dateOfAppointment;
    private int hourOfAppointment;
    private String descriptionOfAppointment;

    private Client assignedClient;

    private List<MedicalService> medicalServiceList = new ArrayList<>();
    private static Set<MedicalService> allMedicalServices = new HashSet<>();

    //todo tutaj popoprawiać tego priv construktor
    private Appointment(LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) throws Exception {
        super();
        this.dateOfAppointment = dateOfAppointment;
        setHourOfAppointment(hourOfAppointment);
        this.descriptionOfAppointment = descriptionOfAppointment;
    }
    //Appointment can only be created by Receptionist with assigned Vet and Client
    public static Appointment createAppointment(Employee receptionist, Employee vet, Client client
            , LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) throws Exception {
        if (receptionist == null || vet == null || client == null){
            return null; //TOD throw exception
        }
        //tod validate if vet is avaible in provided time and if provided client don't have another
        //appointment at provided time
        Appointment appointment = new Appointment(dateOfAppointment, hourOfAppointment, descriptionOfAppointment);
        appointment.assignedClient = client;
        client.addAppointment(appointment);
        return appointment;
    }


    //region Getters and Setters
        public LocalDate getDateOfAppointment() {
            return dateOfAppointment;
        }

        public void setDateOfAppointment(LocalDate dateOfAppointment) {
            this.dateOfAppointment = dateOfAppointment;
        }

        public int getHourOfAppointment() {
            return hourOfAppointment;
        }

        /**
         * Sets hour of appointment.
         * Value must be between opening and closing hours.
         * @param hourOfAppointment int
         * {@value OPENING_HOUR}
         * {@value CLOSING_HOUR}
         * @throws Exception
         */
        public void setHourOfAppointment(int hourOfAppointment) throws Exception {
            if (hourOfAppointment > CLOSING_HOUR || hourOfAppointment < OPENING_HOUR)
            {
                throw new Exception("Hour of appointment must be between working hours");
            }
            this.hourOfAppointment = hourOfAppointment;
        }

        public String getDescriptionOfAppointment() {
            return descriptionOfAppointment;
        }

        /**
         * Updates description of appointment.
         * @param descriptionOfAppointment String
         */
        public void setDescriptionOfAppointment(String descriptionOfAppointment) {
            this.descriptionOfAppointment = descriptionOfAppointment;
        }

        public float getFinalPriceOfAppointment(){
            float finalPrice = MINIMAL_PRICE;
            if (medicalServiceList.isEmpty()){
                return finalPrice;
            }else {
                for (MedicalService medicalService : medicalServiceList) {
                    finalPrice += medicalService.getPriceWithUsageOfMedicine();
                }
            }
            return finalPrice;
        }
    //endregion Getters and Setters

    //region Association MedicalService

        /**
         * Adds medical service and create connection between.
         * @param medicalService
         * @throws ObjectAlreadyInUseException
         */
        public void addMedicalService(MedicalService medicalService) throws ObjectAlreadyInUseException {
            if(medicalService != null && !medicalServiceList.contains(medicalService)) {
                if(allMedicalServices.contains(medicalService)) {
                    throw new ObjectAlreadyInUseException("This medical service is assigned to another appointment.");
                }

                medicalServiceList.add(medicalService);
                allMedicalServices.add(medicalService);
            }
        }


    //endregion Association MedicalService


}
