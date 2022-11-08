package com.petdoctor.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "pet_problem", nullable = false)
    private String petProblem;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vet_clinic_entity_id")
    private VetClinicEntity vetClinicEntity;

    public VetClinicEntity getVetClinicEntity() {
        return vetClinicEntity;
    }

    public void setVetClinicEntity(VetClinicEntity vetClinicEntity) {
        this.vetClinicEntity = vetClinicEntity;
    }

    public ClientEntity() {
    }

    public ClientEntity(Long id,
                        String name,
                        String surname,
                        String email,
                        String petName,
                        String petProblem,
                        List<AppointmentEntity> appointments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.petName = petName;
        this.petProblem = petProblem;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String pet) {
        this.petName = pet;
    }

    public String getPetProblem() {
        return petProblem;
    }

    public void setPetProblem(String petProblem) {
        this.petProblem = petProblem;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}