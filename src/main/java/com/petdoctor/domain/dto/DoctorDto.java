package com.petdoctor.domain.dto;

import java.util.List;

public class DoctorDto {
    private Long id;
    private String name;
    private String email;
    private Integer doctorOffice;
    private List<AppointmentDto> appointments;

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
