package com.mpfinal.mp_final.Model.External;

import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Client extends Person implements Serializable {

    private Map<Integer, Appointment> assignedAppointments = new TreeMap<>();

    public Client(String name, String surname) {
        super(name, surname);
    }

    //TODO - asocjacja kwalifikowana Klient(IdWizyty) * - 1 Wizyta PART 1
    public void addAppointment(Appointment appointment){
        if(appointment == null){
            return; // tod exception
        }
        if(assignedAppointments.isEmpty() || !assignedAppointments.containsKey(appointment.getAppointmentID())) {
            assignedAppointments.put(appointment.getAppointmentID(), appointment);
        }
    }


}
