package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentInterface;

import java.util.Set;


public interface DoctorInterface extends DoctorInfo {

    void setId(Long id);

    void setName(String name);

    void setEmail(String email);

    void setDoctorOffice(Integer doctorOffice);

    void setAppointments(Set<AppointmentInterface> appointments);
}
