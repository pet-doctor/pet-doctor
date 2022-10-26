package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;

import javax.persistence.*;
import java.util.Set;

public class Doctor implements DoctorInterface, DoctorInfo{

    private Long id;
    private String name;
    private String email;
    private Integer doctorOffice;
    private Set<AppointmentInfo> appointments;


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Integer getDoctorOffice() {
        return this.doctorOffice;
    }

    @Override
    public Set<AppointmentInfo> getAppointments() {
        return Set.copyOf(this.appointments);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setDoctorOffice(Integer doctorOffice) {
        this.doctorOffice = doctorOffice;
    }

    @Override
    public void setAppointments(Set<AppointmentInfo> appointments) {
        this.appointments = appointments;
    }
}
