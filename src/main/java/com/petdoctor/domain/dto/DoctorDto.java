package com.petdoctor.domain.dto;

import java.util.List;

public class DoctorDto {
    final private Long id;
    final private String name;
    final private String email;
    final private Integer doctorOffice;
    final private List<AppointmentDto> appointments;

    public DoctorDto(Long id, String name, String email, Integer doctorOffice, List<AppointmentDto> appointments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.doctorOffice = doctorOffice;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getDoctorOffice() {
        return doctorOffice;
    }

    public List<AppointmentDto> getAppointments() {
        return appointments;
    }
}
