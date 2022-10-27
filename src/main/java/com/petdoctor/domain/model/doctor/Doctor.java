package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Doctor implements DoctorInterface {

    private Long id;
    private String name;
    private String email;
    private Integer doctorOffice;
    private Map<Long, AppointmentInterface> appointments;

    public Doctor() {
    }

    public Doctor(Long id, String name, String email, Integer doctorOffice, Map<Long, AppointmentInterface> appointments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.doctorOffice = doctorOffice;
        this.appointments = appointments;
    }

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
    public Map<Long, AppointmentInfo> getAppointments() {
        return Map.copyOf(this.appointments);
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
    public void setAppointments(Map<Long, AppointmentInterface> appointments) {
        this.appointments = appointments;
    }
}