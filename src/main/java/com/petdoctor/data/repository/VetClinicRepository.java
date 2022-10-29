package com.petdoctor.data.repository;

import com.petdoctor.domain.entity.VetClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetClinicRepository extends JpaRepository<Long, VetClinicEntity> {
}
