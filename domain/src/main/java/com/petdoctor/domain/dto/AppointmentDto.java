package com.petdoctor.domain.dto;

import com.petdoctor.domain.model.appointment.AppointmentState;

import java.time.LocalDate;

public class AppointmentDto {
    final private Long id;
    final private LocalDate startTime;
    final private AppointmentState appointmentState;
    final private ClientDto client;
    final private DoctorDto doctorDto;
    final private VetClinicDto vetClinicDto;

    public AppointmentDto(Long id,
                          LocalDate startTime,
                          AppointmentState appointmentState,
                          ClientDto client,
                          DoctorDto doctorDto,
                          VetClinicDto vetClinicDto) {
        this.id = id;
        this.startTime = startTime;
        this.appointmentState = appointmentState;
        this.client = client;
        this.doctorDto = doctorDto;
        this.vetClinicDto = vetClinicDto;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public ClientDto getClient() {
        return client;
    }

    public DoctorDto getDoctorDto() {
        return doctorDto;
    }

    public VetClinicDto getVetClinicDto() {
        return vetClinicDto;
    }
}
