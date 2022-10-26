package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;

import java.util.Set;


public interface DoctorInterface {

    void setId(Long id);
    void setName(String name);
    void setEmail(String email);
    void setDoctorOffice(Integer doctorOffice);
    void setAppointments(Set<AppointmentInfo> appointments);
}
