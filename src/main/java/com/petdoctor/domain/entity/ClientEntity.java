package com.petdoctor.domain.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @Column(name = "pet", nullable = false)
    private String pet;

    @Column(name = "pet_problem", nullable = false)
    private String petProblem;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AppointmentEntity> appointments = new LinkedHashSet<>();

    public ClientEntity() {
    }

    public ClientEntity(Long id,
                        String name,
                        String surname,
                        String email,
                        String pet,
                        String petProblem,
                        Set<AppointmentEntity> appointments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pet = pet;
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

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getPetProblem() {
        return petProblem;
    }

    public void setPetProblem(String petProblem) {
        this.petProblem = petProblem;
    }

    public Set<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}