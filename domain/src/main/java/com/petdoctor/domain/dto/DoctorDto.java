package com.petdoctor.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class DoctorDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer doctorOffice;
    private ArrayList<AppointmentDto> appointments = new ArrayList<>();

    public DoctorDto() {}

    public DoctorDto(Long id, String name, String surname, String email, Integer doctorOffice, ArrayList<AppointmentDto> appointments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public Integer getDoctorOffice() {
        return doctorOffice;
    }

    public ArrayList<AppointmentDto> getAppointments() {
        return appointments;
    }
}
