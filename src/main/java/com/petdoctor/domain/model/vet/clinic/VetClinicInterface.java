package com.petdoctor.domain.model.vet.clinic;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.client.ClientInterface;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.doctor.DoctorInterface;

public interface VetClinicInterface extends VetClinicInfo{

    AppointmentInfo closeAppointment(AppointmentInfo appointmentInfo);

    AppointmentInfo bookAppointment(AppointmentInfo appointmentInfo);

    AppointmentInfo addAppointment(AppointmentInfo appointmentInfo);

    void deleteAppointment(AppointmentInfo appointmentInfo);

    DoctorInfo addDoctor(DoctorInfo doctor);

    ClientInfo addClient(ClientInfo client);
}
