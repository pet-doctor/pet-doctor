package com.petdoctor.domain.dto;

import java.util.ArrayList;

public class VetClinicDto {
    private Long id;
    private String address;
    private String email;
    private ArrayList<DoctorDto> doctors;
    private ArrayList<ClientDto> clients;


    public VetClinicDto(Long id, String address, String email, ArrayList<DoctorDto> doctors, ArrayList<ClientDto> clients) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.doctors = doctors;
        this.clients = clients;
    }

    protected VetClinicDto() {
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

    public ArrayList<DoctorDto> getDoctors() {
        return doctors;
    }

    public ArrayList<ClientDto> getClients() {
        return clients;
    }
}
