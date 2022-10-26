package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInfo;

import java.util.Set;

public interface DoctorInfo {

    Long getId();
    String getName();
    String getEmail();
    Integer getDoctorOffice();
    Set<AppointmentInfo> getAppointments();
}
