package com.petdoctor.domain.model.appointment;

import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public interface AppointmentInterface {

    void setId(Long id);
    void setStartTime(LocalDate startTime);
    void setAppointmentState(AppointmentState appointmentState);
    void setClient(ClientInfo client);
    void setDoctor(DoctorInfo doctor);
}
