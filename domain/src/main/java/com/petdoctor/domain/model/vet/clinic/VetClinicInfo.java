package com.petdoctor.domain.model.vet.clinic;

import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;

import java.util.Map;

public interface VetClinicInfo {
    Long getId();

    String getAddress();

    String getEmail();

    Map<Long, DoctorInfo> getDoctors();

    Map<Long, ClientInfo> getClients();
}
