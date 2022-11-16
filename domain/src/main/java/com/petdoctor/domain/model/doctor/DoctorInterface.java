package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentInterface;

import java.util.HashMap;


public interface DoctorInterface extends DoctorInfo {

    void setId(Long id);

    void setName(String name);

    void setEmail(String email);

    void setDoctorOffice(Integer doctorOffice);

    void setAppointments(HashMap<Long, AppointmentInterface> appointments);

    AppointmentInfo addAppointment(AppointmentInfo appointmentInfo);

    AppointmentInfo bookAppointment(AppointmentInfo appointmentInfo);

    AppointmentInfo closeAppointment(AppointmentInfo appointmentInfo);

    void deleteAppointment(AppointmentInfo appointmentInfo);
}
