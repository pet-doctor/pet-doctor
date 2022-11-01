package com.petdoctor.domain.dto;

import java.util.List;

public class VetClinicDto {
    final private Long id;
    final private String address;
    final private String email;
    final private List<DoctorDto> doctors;
    final private List<ClientDto> clients;


    public VetClinicDto(Long id, String address, String email, List<DoctorDto> doctors, List<ClientDto> clients) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.doctors = doctors;
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public List<DoctorDto> getDoctors() {
        return doctors;
    }

    public List<ClientDto> getClients() {
        return clients;
    }
}
