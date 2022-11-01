package com.petdoctor.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<ClientEntity> clientEntities = new ArrayList<>();

    @OneToMany(mappedBy = "vetClinicEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorEntity> doctorEntities = new ArrayList<>();

    public List<DoctorEntity> getDoctorEntities() {
        return doctorEntities;
    }

    public void setDoctorEntities(List<DoctorEntity> doctorEntities) {
        this.doctorEntities = doctorEntities;
    }

    public List<ClientEntity> getClientEntities() {
        return clientEntities;
    }

    public void setClientEntities(List<ClientEntity> clientEntities) {
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
