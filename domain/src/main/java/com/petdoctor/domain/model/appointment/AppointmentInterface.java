package com.petdoctor.domain.model.appointment;

import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInfo;

import java.time.LocalDate;

public interface AppointmentInterface extends AppointmentInfo {

    void setId(Long id);

    void setStartTime(LocalDate startTime);

    void setAppointmentState(AppointmentState appointmentState);

    void setClient(ClientInfo client);

    void setDoctor(DoctorInfo doctor);

    void setVetClinic(VetClinicInfo vetClinic);
}
