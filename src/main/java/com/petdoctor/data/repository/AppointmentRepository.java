package com.petdoctor.data.repository;

import com.petdoctor.domain.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Long, AppointmentEntity> {
}
