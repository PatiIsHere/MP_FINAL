package com.mpfinal.mp_final.Model.Internal;

import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;

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

    public Employee(String name, String surname, LocalDate beginningOfEmployment, boolean higherEducation, EmployeeRoles role) {
        super(name, surname);
        this.beginningOfEmployment = beginningOfEmployment;
        this.higherEducation = higherEducation;
        this.roles.add(role);
    }

//region Getters and Setters
    public LocalDate getBeginningOfEmployment() {
        return beginningOfEmployment;
    }

    public void setBeginningOfEmployment(LocalDate beginningOfEmployment) {
        this.beginningOfEmployment = beginningOfEmployment;
    }

    public LocalDate getEndOfEmployment() {
        return endOfEmployment;
    }

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

    public void setHigherEducation(boolean higherEducation) {
        this.higherEducation = higherEducation;
    }

//endregion Getters and Setters

//region Vet
    public boolean isVet(){
        if(this.roles.contains(EmployeeRoles.VET)){
            return true;
        }
        return false;
    }

    public boolean isVetAvaible(LocalDate suggestedDate, int suggestedHour){
        if(!this.isVet()){
            return false;
        }
        //return false if appointment with provided data is found
        return !this.assignedAppointments.stream()
                .filter(e -> e.getDateOfAppointment().isEqual(suggestedDate))
                .anyMatch(a -> a.getHourOfAppointment() == suggestedHour);

    }

    public boolean isBonusAcquired() throws Exception {


        boolean bonusAcquired = assignedAppointments.stream()
                        .filter(e -> isAppointmentDateInThisMonth(e.getDateOfAppointment()))
                        .anyMatch(a -> a.getDescriptionOfAppointment().contains("chip"));

        return bonusAcquired;
    }

    private boolean isAppointmentDateInThisMonth(LocalDate appointmentDate){
        LocalDate firstDayOfCurrentMonth = YearMonth.now().atDay(1);
        LocalDate lastDayOfCurrentMonth = YearMonth.now().atEndOfMonth();
        return !(appointmentDate.isBefore(firstDayOfCurrentMonth) || appointmentDate.isAfter(lastDayOfCurrentMonth));
    }

//endregion Vet

    public int getBaseVacationDays(){
        if(this.endOfEmployment != null){
            return 0;
        }

        int baseVacationDays = 6;
        if (higherEducation){
            baseVacationDays += 2;
        }

        return (int) (baseVacationDays + ChronoUnit.YEARS.between(beginningOfEmployment, LocalDate.now()));
    }
}
