package com.petdoctor.domain.model.appointment;

import com.petdoctor.data.entity.AppointmentState;
import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInfo;

import java.time.LocalDate;


public interface AppointmentInfo {
    Long getId();

    LocalDate getStartTime();

    AppointmentState getAppointmentState();

    ClientInfo getClient();

    DoctorInfo getDoctor();

    VetClinicInfo getVetClinic();
}