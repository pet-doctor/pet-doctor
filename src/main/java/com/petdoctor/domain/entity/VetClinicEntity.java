package com.petdoctor.domain.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class VetClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "vetClinicEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientEntity> clientEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vetClinicEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorEntity> doctorEntities = new LinkedHashSet<>();

    public Set<DoctorEntity> getDoctorEntities() {
        return doctorEntities;
    }

    public void setDoctorEntities(Set<DoctorEntity> doctorEntities) {
        this.doctorEntities = doctorEntities;
    }

    public Set<ClientEntity> getClientEntities() {
        return clientEntities;
    }

    public void setClientEntities(Set<ClientEntity> clientEntities) {
        this.clientEntities = clientEntities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
