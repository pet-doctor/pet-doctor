package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;

import java.util.Map;

public interface DoctorInfo {

    Long getId();

    String getName();

    String getEmail();

    Integer getDoctorOffice();

    Map<Long, AppointmentInfo> getAppointments();
}