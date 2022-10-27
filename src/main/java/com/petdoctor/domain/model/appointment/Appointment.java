package com.petdoctor.domain.model.appointment;

import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;

import java.time.LocalDate;

public class Appointment implements AppointmentInterface {

    private Long id;
    private LocalDate startTime;
    private AppointmentState appointmentState;
    private ClientInfo client;
    private DoctorInfo doctor;

    public Appointment() {
    }

    public Appointment(Long id, LocalDate startTime, AppointmentState appointmentState, ClientInfo clientInfo, DoctorInfo doctor) {
        this.id = id;
        this.startTime = startTime;
        this.appointmentState = appointmentState;
        client = clientInfo;
        this.doctor = doctor;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public LocalDate getStartTime() {
        return this.startTime;
    }

    @Override
    public AppointmentState getAppointmentState() {
        return this.appointmentState;
    }

    @Override
    public ClientInfo getClient() {
        return this.client;
    }

    @Override
    public DoctorInfo getDoctor() {
        return this.doctor;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setAppointmentState(AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }

    @Override
    public void setClient(ClientInfo client) {
        this.client = client;
    }

    @Override
    public void setDoctor(DoctorInfo doctor) {
        this.doctor = doctor;
    }
}
