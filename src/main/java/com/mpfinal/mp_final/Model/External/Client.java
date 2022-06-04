package com.mpfinal.mp_final.Model.External;

import com.mpfinal.mp_final.Model.Base.Person;
import com.mpfinal.mp_final.Model.ClinicServices.Appointment;
import com.mpfinal.mp_final.Model.System.IDGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Client extends Person implements Serializable {

    private int clientID;
    private Map<Integer, Appointment> assignedAppointments = new TreeMap<>();

    public Client(String name, String surname) {
        super(name, surname);
        clientID = IDGenerator.generateUniqueID();
    }

    public void addAppointment(Appointment appointment){
        if(appointment == null){
            return; // todo exception
        }
        if(assignedAppointments.isEmpty() || !assignedAppointments.containsKey(appointment.getAppointmentID())) {
            assignedAppointments.put(appointment.getAppointmentID(), appointment);
        }
    }


}
