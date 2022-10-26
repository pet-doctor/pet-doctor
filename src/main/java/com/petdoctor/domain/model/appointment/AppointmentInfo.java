package com.petdoctor.domain.model.appointment;

import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;

import javax.persistence.Entity;
import java.time.LocalDate;


public interface AppointmentInfo {
    Long getId();
    LocalDate getStartTime();
    AppointmentState getAppointmentState();
    ClientInfo getClient();
    DoctorInfo getDoctor();
}