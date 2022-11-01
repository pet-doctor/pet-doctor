package com.petdoctor.domain.dto;

public class ClientDto {
    final private Long id;
    final private String name;
    final private String surname;
    final private String email;
    final private String petName;
    final private String petProblem;


    public ClientDto(Long id, String name, String surname, String email, String petName, String petProblem) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.petName = petName;
        this.petProblem = petProblem;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPetName() {
        return petName;
    }

    public String getPetProblem() {
        return petProblem;
    }
}
