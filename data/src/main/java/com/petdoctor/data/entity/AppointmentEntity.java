package com.petdoctor.data.entity;

import com.petdoctor.domain.model.appointment.AppointmentState;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AppointmentEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDate startTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "appointment_state", nullable = false)
    private AppointmentState appointmentState;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    @ManyToOne
    @JoinColumn(name = "doctor_entity_id")
    private DoctorEntity doctorEntity;

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public AppointmentEntity() {
    }

    public AppointmentEntity(Long id, LocalDate startTime, AppointmentState appointmentState) {
        this.id = id;
        this.startTime = startTime;
        this.appointmentState = appointmentState;
    }

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
}
