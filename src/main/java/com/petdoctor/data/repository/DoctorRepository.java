package com.petdoctor.data.repository;

import com.petdoctor.domain.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Long, DoctorEntity> {
}
