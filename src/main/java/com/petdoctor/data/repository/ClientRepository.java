package com.petdoctor.data.repository;

import com.petdoctor.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Long, ClientEntity> {
}
