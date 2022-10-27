package com.petdoctor.domain.model.client;

public interface ClientInterface extends ClientInfo {

    void setId(Long id);

    void setName(String name);

    void setEmail(String email);

    void setPetName(String petName);

    void setPetProblem(String petProblem);
}
