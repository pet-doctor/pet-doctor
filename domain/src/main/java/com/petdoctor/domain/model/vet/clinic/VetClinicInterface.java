package com.petdoctor.domain.model.vet.clinic;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.client.ClientInterface;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.doctor.DoctorInterface;

import java.util.Map;

public interface VetClinicInterface extends VetClinicInfo {

    void setId(Long id);

    void setAddress(String address);

    void setEmail(String email);

    void setDoctors(Map<Long, DoctorInterface> doctors);

    void setClients(Map<Long, ClientInterface> clients);

    AppointmentInfo closeAppointment(AppointmentInfo appointmentInfo);

    AppointmentInfo bookAppointment(AppointmentInfo appointmentInfo);

    AppointmentInfo addAppointment(AppointmentInfo appointmentInfo);

    void deleteAppointment(AppointmentInfo appointmentInfo);

    DoctorInfo addDoctor(DoctorInfo doctor);

    ClientInfo addClient(ClientInfo client);
}
