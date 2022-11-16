package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;

import java.util.HashMap;

public interface DoctorInfo {

    Long getId();

    String getName();

    String getEmail();

    Integer getDoctorOffice();

    HashMap<Long, AppointmentInfo> getAppointments();
}
