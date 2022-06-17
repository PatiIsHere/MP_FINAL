package com.mpfinal.mp_final.Model.Internal;

import com.mpfinal.mp_final.Model.Base.Address;
import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.CustomModelExceptions.DoubleAssignmentException;
import com.mpfinal.mp_final.Model.CustomModelExceptions.EmployeeRoleException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Employee extends Person implements Serializable {
    private LocalDate beginningOfEmployment;
    private LocalDate endOfEmployment;
    private boolean higherEducation;
    private EnumSet<EmployeeRoles> roles;

    private Set<Appointment> createdAppointments = new HashSet<>();
    private Set<Appointment> assignedAppointments = new HashSet<>();

    /**
     * Constructor.
     * @param name String
     * @param surname String
     * @param address Adress
     * @param beginningOfEmployment LocalDate
     * @param higherEducation boolean
     * @param role EmployeeRole
     */
    public Employee(String name, String surname, Address address, LocalDate beginningOfEmployment, boolean higherEducation, EmployeeRoles role) {
        super(name, surname, address);
        setBeginningOfEmployment(beginningOfEmployment);
        setHigherEducation(higherEducation);
        this.roles = EnumSet.of(role);
    }

    //region Getters and Setters
        public LocalDate getBeginningOfEmployment() {
            return beginningOfEmployment;
        }

        /**
         * Sets the beginning of employment.
         * @param beginningOfEmployment LocalDate
         */
        public void setBeginningOfEmployment(LocalDate beginningOfEmployment) {
            this.beginningOfEmployment = beginningOfEmployment;
        }

        public LocalDate getEndOfEmployment() {
            return endOfEmployment;
        }

        /**
         * Sets the end of employment.
         * @param endOfEmployment
         * @throws Exception when endOfEmployment if before startOfEmployment
         */
        public void setEndOfEmployment(LocalDate endOfEmployment) throws Exception {
            if(endOfEmployment.isBefore(this.beginningOfEmployment)){
                throw new Exception("Termination of employment cannot be before beginning of employment");
            }
            this.endOfEmployment = endOfEmployment;
            this.roles.clear();
        }

        public boolean isHigherEducation() {
            return higherEducation;
        }

        /**
         * Sets status if employee has higher education.
         * @param higherEducation boolean
         */
        public void setHigherEducation(boolean higherEducation) {
            this.higherEducation = higherEducation;
        }

        public EnumSet<EmployeeRoles> getRoles() {
            return roles;
        }
        public void addRole(EmployeeRoles role){
            if(!this.roles.contains(role)){
                roles.add(role);
            }
        }

    //endregion Getters and Setters

    //region Receptionist
    public boolean isReceptionist(){
        return this.roles.contains(EmployeeRoles.RECEPCIONIST);
    }

        //region Association Appointment

            /**
             * Returns true if employee is receptionist and is not assigned as vet.
             * @param appointment Appointment
             * @return boolean
             * @throws EmployeeRoleException when employee is not receptionist
             * @throws DoubleAssignmentException when employee is already assigned as vet
             */
            public boolean canReceptionistBeAssignedToAppointment(Appointment appointment) throws EmployeeRoleException, DoubleAssignmentException {
                if(!isReceptionist()){
                    throw new EmployeeRoleException("This employee is not Receptionist!");
                }

                if(roles.contains(EmployeeRoles.VET) && assignedAppointments.contains(appointment)){
                    throw new DoubleAssignmentException("This employee is assigned as vet, cannot be assigned as receptionist!");
                }

                return true;
            }

            /**
             * Adds appointment to createdAppointments and creates connection between.
             * @param appointment
             * {@link #canReceptionistBeAssignedToAppointment(Appointment)}
             * @throws EmployeeRoleException when employee is not receptionist
             * @throws DoubleAssignmentException when employee is already assigned as vet
             */
            public void addAppointmentToReceptionist(Appointment appointment) throws EmployeeRoleException, DoubleAssignmentException {
                if (appointment != null){
                    if(canReceptionistBeAssignedToAppointment(appointment)) {
                        if (!createdAppointments.contains(appointment)) {
                            createdAppointments.add(appointment);
                            appointment.addReceptionist(this);
                        }
                    }
                }
            }

            /**
             * Removes appointment and connection between.
             * @param appointment Appointment
             */
            public void removeAppointmentFromReceptionist(Appointment appointment){
                if(createdAppointments.contains(appointment)){
                    createdAppointments.remove(appointment);
                    appointment.removeReceptionist(this);
                }

            }

        //endregion Association Appointment

    //endregion Receptionist

    //region Vet
        public boolean isVet(){
            return this.roles.contains(EmployeeRoles.VET);
        }

        /**
         * Returns false if vet does have scheduled appointment in provided date and hour or employee is not vet.
         * @param suggestedDate LocalDate
         * @param suggestedHour LocalDate
         * @return boolean
         */
        public boolean isVetAvaible(LocalDate suggestedDate, int suggestedHour){
            if(!this.isVet()){
                return false;
            }
            return this.assignedAppointments.stream()
                    .filter(e -> e.getDateOfAppointment().isEqual(suggestedDate))
                    .noneMatch(a -> a.getHourOfAppointment() == suggestedHour);
        }

        /**
         * Returns false if no appointment in current month contained key-word 'chip' or employee is not vet.
         * {@link #isAppointmentDateInThisMonth(LocalDate)}
         * @return boolean
         */
        public boolean isBonusAcquired() {
            if(!this.isVet()){
                return false;
            }
            return assignedAppointments.stream()
                            .filter(e -> isAppointmentDateInThisMonth(e.getDateOfAppointment()))
                            .anyMatch(a -> a.getDescriptionOfAppointment().contains("chip"));
        }

        /**
         * Verifies if date is in current month.
         * @param appointmentDate LocalDate
         * @return boolean
         */
        private boolean isAppointmentDateInThisMonth(LocalDate appointmentDate){
            LocalDate firstDayOfCurrentMonth = YearMonth.now().atDay(1);
            LocalDate lastDayOfCurrentMonth = YearMonth.now().atEndOfMonth();
            return !(appointmentDate.isBefore(firstDayOfCurrentMonth) || appointmentDate.isAfter(lastDayOfCurrentMonth));
        }

        //region Association Appointment

            /**
             * Returns true if employee is vet and is not assigned as receptionist.
             * @param appointment Appointment
             * @return boolean
             * @throws EmployeeRoleException when employee is not vet
             * @throws DoubleAssignmentException when employee is already assigned as receptionist
             */
            public boolean canVetBeAssignedToAppointment(Appointment appointment) throws EmployeeRoleException, DoubleAssignmentException {
                if(!isVet()){
                    throw new EmployeeRoleException("This employee is not Vet!");
                }

                if(roles.contains(EmployeeRoles.RECEPCIONIST) && createdAppointments.contains(appointment)){
                    throw new DoubleAssignmentException("This employee is assigned as receptionist, cannot be assigned as vet!");
                }

                return true;
            }

            /**
             * Adds appointment to assignedAppointments and creates connection between.
             * @param appointment
             * {@link #canVetBeAssignedToAppointment(Appointment)}
             * @throws EmployeeRoleException when employee is not vet
             * @throws DoubleAssignmentException when employee is already assigned as receptionist
             */
            public void addAppointmentToVet(Appointment appointment) throws EmployeeRoleException, DoubleAssignmentException {
                if (appointment != null){
                    if(canVetBeAssignedToAppointment(appointment)) {
                        if (!assignedAppointments.contains(appointment)) {
                            assignedAppointments.add(appointment);
                            appointment.addVet(this);
                        }
                    }
                }
            }

            /**
             * Removes appointment from assignedAppointments and connection between.
             * @param appointment Appointment
             */
            public void removeAppointmentFromVet(Appointment appointment){
                if(assignedAppointments.contains(appointment)){
                    assignedAppointments.remove(appointment);
                    appointment.removeVet(this);
                }

            }
        //endregion Association Appointment

    //endregion Vet

    //TODO ogarnac to bo kiszka
    public int getBaseVacationDays(){
        if(this.endOfEmployment != null){
            return 0;
        }
        int baseVacationDays = 20;
        if (higherEducation){
            baseVacationDays += 6;
        }
        return (int) (baseVacationDays + ChronoUnit.YEARS.between(beginningOfEmployment, LocalDate.now()));
    }
}
