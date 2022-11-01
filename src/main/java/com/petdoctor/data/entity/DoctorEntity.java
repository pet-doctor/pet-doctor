package com.petdoctor.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "doctor_office", nullable = false, unique = true)
    private Integer doctorOffice;

    @OneToMany(mappedBy = "doctorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointmentEntities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vet_clinic_entity_id")
    private VetClinicEntity vetClinicEntity;

    public VetClinicEntity getVetClinicEntity() {
        return vetClinicEntity;
    }

    public void setVetClinicEntity(VetClinicEntity vetClinicEntity) {
        this.vetClinicEntity = vetClinicEntity;
    }

    public List<AppointmentEntity> getAppointmentEntities() {
        return appointmentEntities;
    }

    public void setAppointmentEntities(List<AppointmentEntity> appointmentEntities) {
        this.appointmentEntities = appointmentEntities;
    }

    public Integer getDoctorOffice() {
        return doctorOffice;
    }

    public void setDoctorOffice(Integer doctorOffice) {
        this.doctorOffice = doctorOffice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
