package com.petdoctor.domain.dto;

import com.petdoctor.domain.dto.lite.AppointmentLiteDto;

import java.util.List;

public class DoctorDto {
    final private Long id;
    final private String name;
    final private String email;
    final private Integer doctorOffice;
    final private List<AppointmentLiteDto> appointments;

    public DoctorDto(Long id, String name, String email, Integer doctorOffice, List<AppointmentLiteDto> appointments) {
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

    public List<AppointmentLiteDto> getAppointments() {
        return appointments;
    }
}
