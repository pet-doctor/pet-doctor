package com.petdoctor.domain.dto;

import com.petdoctor.domain.model.appointment.AppointmentState;

import java.time.LocalDate;

public class AppointmentDto {
    private Long id;
    private LocalDate startTime;
    private AppointmentState appointmentState;
    private ClientDto client;
    private DoctorDto doctorDto;
    private VetClinicDto vetClinicDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public void setAppointmentState(AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public DoctorDto getDoctorDto() {
        return doctorDto;
    }

    public void setDoctorDto(DoctorDto doctorDto) {
        this.doctorDto = doctorDto;
    }

    public VetClinicDto getVetClinicDto() {
        return vetClinicDto;
    }

    public void setVetClinicDto(VetClinicDto vetClinicDto) {
        this.vetClinicDto = vetClinicDto;
    }
}
