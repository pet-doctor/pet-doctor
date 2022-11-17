package com.petdoctor.data.repository;

import com.petdoctor.data.entity.VetClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetClinicRepository extends JpaRepository<VetClinicEntity, Long> {
}
