package com.petdoctor.domain.model.client;

public class Client implements ClientInterface {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String petName;
    private String petProblem;

    public Client() {
    }

    /**
     * @param id (Long) - unique identifier in the database
     * @param name (String) - name of the doctor
     * @param surname (String) - surname of the doctor
     * @param email (String) - email of the doctor
     * @param petName (String) - name of the pet
     * @param petProblem (String) - current problem of the client's pet
     */
    public Client(Long id, String name, String surname, String email, String petName, String petProblem) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.petName = petName;
        this.petProblem = petProblem;
    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPetName() {
        return this.petName;
    }

    @Override
    public String getPetProblem() {
        return this.petProblem;
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
    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Override
    public void setPetProblem(String petProblem) {
        this.petProblem = petProblem;
    }
}
