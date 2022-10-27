package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentInterface;

import java.util.Map;
import java.util.Set;

public interface DoctorInfo {

    Long getId();
    String getName();
    String getEmail();
    Integer getDoctorOffice();
    Map<Long, AppointmentInfo>  getAppointments();
}