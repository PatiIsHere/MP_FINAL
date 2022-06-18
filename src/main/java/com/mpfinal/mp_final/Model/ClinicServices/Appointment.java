package com.mpfinal.mp_final.Model.ClinicServices;

import com.mpfinal.mp_final.Model.Animals.MedicalCard;
import com.mpfinal.mp_final.Model.CustomModelExceptions.DoubleAssignmentException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.EmployeeRoleException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.ObjectAlreadyInUseException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.OpeningHoursException;
import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.Internal.Employee;
import com.mpfinal.mp_final.Model.System.ExtensionManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Appointment extends ExtensionManager implements Serializable {
    public static final int OPENING_HOUR = 8;
    public static final int CLOSING_HOUR = 21;
    private static final float MINIMAL_PRICE = 49.99f;

    private LocalDate dateOfAppointment;
    private int hourOfAppointment;
    private String descriptionOfAppointment;
    private List<MedicalService> medicalServiceList = new ArrayList<>();
    private static Set<MedicalService> allMedicalServices = new HashSet<>();

    private Employee receptionist;
    private Employee vet;

    private List<Client> clients = new ArrayList<>();
    private MedicalCard medicalCard;

    /**
     * Constructor.
     * @param dateOfAppointment LocalDate
     * @param hourOfAppointment int
     * @param descriptionOfAppointment String
     * @throws OpeningHoursException when hourOfAppointment is not between OPENING_HOUR and CLOSING_HOUR
     */
    public Appointment(LocalDate dateOfAppointment, int hourOfAppointment, String descriptionOfAppointment) throws OpeningHoursException {
        setDateOfAppointment(dateOfAppointment);
        setHourOfAppointment(hourOfAppointment);
        setDescriptionOfAppointment(descriptionOfAppointment);
    }

    //region Getters and Setters
        public LocalDate getDateOfAppointment() {
            return dateOfAppointment;
        }

        /**
         * Sets the date of appointment.
         * @param dateOfAppointment LocalDate
         */
        public void setDateOfAppointment(LocalDate dateOfAppointment) {
            //TODO verify if date and hour is not < LocalDate.Now()
            this.dateOfAppointment = dateOfAppointment;
        }

        public int getHourOfAppointment() {
            return hourOfAppointment;
        }

        /**
         * Sets the hour of appointment.
         * Value must be between opening and closing hours.
         * @param hourOfAppointment int
         * {@value OPENING_HOUR}
         * {@value CLOSING_HOUR}
         * @throws OpeningHoursException when hourOfAppointment is not between OPENING_HOUR and CLOSING_HOUR
         */
        public void setHourOfAppointment(int hourOfAppointment) throws OpeningHoursException {
            //TODO verify if date and hour is not < LocalDate.Now()
            if (hourOfAppointment > CLOSING_HOUR || hourOfAppointment < OPENING_HOUR)
            {
                throw new OpeningHoursException("Hour of appointment must be between " +
                        OPENING_HOUR +
                        " and " +
                        CLOSING_HOUR +
                        ".");
            }
            this.hourOfAppointment = hourOfAppointment;
        }

        public String getDescriptionOfAppointment() {
            return descriptionOfAppointment;
        }

        /**
         * Updates the description of appointment.
         * @param descriptionOfAppointment String
         */
        public void setDescriptionOfAppointment(String descriptionOfAppointment) {
            this.descriptionOfAppointment = descriptionOfAppointment;
        }

        /**
         * Adds cost of every medical service to minimal price and returns final price.
         * {@value MINIMAL_PRICE}
         * @return float
         */
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

        public List<MedicalService> getMedicalServiceList() {
            return medicalServiceList;
        }

    //endregion Getters and Setters

    //region Association MedicalCard

    /**
     * Adds medical card and creates connection if medical card is null.
     * If not - removes existing connection and create a new one.
     * (recursive call after setting this.medicalCard = null)
     * @param medicalCard MedicalCard
     * {@link #removeMedicalCard(MedicalCard)}
     **/
    public void addMedicalCard(MedicalCard medicalCard){
        if(medicalCard != null ){
            if(this.medicalCard == null) {
                this.medicalCard = medicalCard;
                medicalCard.addAppointment(this);
            }else if(this.medicalCard.getId() != medicalCard.getId()) {
                removeMedicalCard(this.medicalCard);
                addMedicalCard(medicalCard);
                medicalCard.addAppointment(this);
            }
        }
    }

    /**
     * Removes medical card and connection between.
     * @param medicalCard MedicalCard
     **/
    public void removeMedicalCard(MedicalCard medicalCard){
        if(medicalCard != null && this.medicalCard != null && this.medicalCard.getId() == medicalCard.getId()){
            this.medicalCard = null;
            medicalCard.removeAppointment(this);
        }
    }


    //endregion Association MedicalCard

    //region Composition MedicalService

        /**
         * Adds medical service and creates connection between.
         * @param medicalService MedicalService
         * @throws ObjectAlreadyInUseException when medical service is already assigned to appointment.
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

    //endregion Composition MedicalService

    //region Association Client

        /**
         * Adds client and creates connection between.
         * @param client Client
         */
        public void addClient(Client client) {
            if(!clients.contains(client)){
                clients.add(client);
                client.addAppointment(this);
            }
        }

        /**
         * Removes client and connection between.
         * @param client Client
         */
        public void removeClient(Client client){
            if(clients.contains(client)){
                clients.remove(client);
                client.removeAppointment(this);
            }

        }

    //endregion Association Client

    //region Association Receptionist

        /**
         * Adds receptionist and creates connection if receptionist is null.
         * If not - removes existing connection and create a new one.
         * Receptionist cannot be assigned if same object is assigned as vet.
         * (recursive call after setting this.receptionist = null)
         * @param receptionist Employee
         * @see Employee#canReceptionistBeAssignedToAppointment(Appointment)
         * @throws EmployeeRoleException when employee does not have EmployeeRole.VET
         * @throws DoubleAssignmentException when employee is already assigned as vet
         */
        public void addReceptionist(Employee receptionist) throws EmployeeRoleException, DoubleAssignmentException {
            if(receptionist != null ){
                if(receptionist.canReceptionistBeAssignedToAppointment(this)) {
                    if (this.receptionist == null) {
                        this.receptionist = receptionist;
                        receptionist.addAppointmentToReceptionist(this);
                    } else if(this.receptionist.getId() != receptionist.getId()) {
                        removeReceptionist(this.receptionist);
                        addReceptionist(receptionist);
                        receptionist.addAppointmentToReceptionist(this);
                    }
                }
            }
        }

        /**
         * Removes receptionist and connection between.
         * @param receptionist Employee
         */
        public void removeReceptionist(Employee receptionist){
            if(receptionist != null && this.receptionist != null && this.receptionist.getId() == receptionist.getId()){
                this.receptionist = null;
                receptionist.removeAppointmentFromReceptionist(this);
            }
        }

    //endregion Association Receptionist

    //region Association Vet

        /**
         * Adds vet and creates connection if vet is null.
         * If not - removes existing connection and create a new one.
         * Vet cannot be assigned if same object is assigned as receptionist.
         * (recursive call after setting this.receptionist = null)
         * @param vet Employee
         * @see Employee#canVetBeAssignedToAppointment(Appointment) 
         * @throws EmployeeRoleException when employee does not have EmployeeRole.RECEPTIONIST
         * @throws DoubleAssignmentException when employee is already assigned as receptionist
         */
        public void addVet(Employee vet) throws EmployeeRoleException, DoubleAssignmentException{
            if(vet != null ){
                if(vet.canVetBeAssignedToAppointment(this)) {
                    if (this.vet == null) {
                        this.vet = vet;
                        vet.addAppointmentToVet(this);
                    } else if(this.vet.getId() != vet.getId()) {
                        removeVet(this.vet);
                        addVet(vet);
                        vet.addAppointmentToVet(this);
                    }
                }
            }
        }

        /**
         * Removes vet and connection between
         * @param vet Employee
         */
        public void removeVet(Employee vet){
            if(vet != null && this.vet != null && this.vet.getId() == vet.getId()){
                this.vet = null;
                vet.removeAppointmentFromVet(this);
            }
        }
    //endregion Association Vet


}
