package com.petdoctor.domain.service.impl;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.entity.VetClinicEntity;
import com.petdoctor.data.repository.ClientRepository;
import com.petdoctor.data.repository.VetClinicRepository;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.model.vet.clinic.VetClinicInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInterface;
import com.petdoctor.domain.service.DoctorService;
import com.petdoctor.domain.service.VetClinicService;
import com.petdoctor.domain.tool.exception.PetDoctorNotFoundException;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import com.petdoctor.domain.tool.exception.PetDoctorValidationException;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class VetClinicServiceImpl implements VetClinicService {

    private DoctorService doctorService;
//    TODO: implement repositories
    private final VetClinicRepository vetClinicRepository;
    private final Mapper<VetClinicEntity, VetClinic, VetClinicDto> vetClinicMapper;

    @Autowired
    public VetClinicServiceImpl(VetClinicRepository vetClinicRepository,
                                Mapper<VetClinicEntity, VetClinic, VetClinicDto> vetClinicMapper) {
        this.vetClinicRepository = vetClinicRepository;
        this.vetClinicMapper = vetClinicMapper;
    }

    @Override
    public List<VetClinicDto> findAllVetClinic() {
        List<VetClinicInfo> vetClinicInfos = vetClinicRepository
                .findAll()
                .stream()
                .map(vetClinicEntity -> (VetClinicInfo) vetClinicMapper.toModelFromEntity(vetClinicEntity))
                .toList();

        return vetClinicInfos
                .stream()
                .map(vetClinicInfo -> vetClinicMapper.toDtoFromModel((VetClinic) vetClinicInfo))
                .toList();
    }

    @Override
    public VetClinicDto getVetClinicById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }

        try {
            VetClinicInfo vetClinicInfo = vetClinicMapper.toModelFromEntity(vetClinicRepository.getReferenceById(id));
            return vetClinicMapper.toDtoFromModel((VetClinic) vetClinicInfo);
        } catch (EntityNotFoundException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }

    @Override
    public VetClinicDto saveVetClinic(VetClinicDto vetClinicDto) {
        if (vetClinicDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        VetClinicInfo vetClinicInfo = vetClinicMapper.toModelFromDto(vetClinicDto);
        var vetClinicEntity =
                vetClinicRepository.save(vetClinicMapper.toEntityFromModel((VetClinic) vetClinicInfo));
        return vetClinicMapper.toDtoFromModel(vetClinicMapper.toModelFromEntity(vetClinicEntity));
    }

    @Override
    public VetClinicDto updateVetClinic(VetClinicDto vetClinicDto) {
        if (vetClinicDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        try {
            VetClinicDto oldVetClinic = this.getVetClinicById(vetClinicDto.getId());
            String address = oldVetClinic.getAddress();
            String email = oldVetClinic.getEmail();
            List<ClientDto> clients = oldVetClinic.getClients();
            List<DoctorDto> doctors = oldVetClinic.getDoctors();

            if (oldVetClinic.getAddress() != null) {
                address = vetClinicDto.getAddress();
            }

            if (oldVetClinic.getEmail() != null) {
                email = vetClinicDto.getEmail();
            }

            if (oldVetClinic.getClients() != null) {
                clients = vetClinicDto.getClients();
            }

            if (oldVetClinic.getDoctors() != null) {
                doctors = vetClinicDto.getDoctors();
            }

            var updatedVetClinicDto = new VetClinicDto(oldVetClinic.getId(),
                    address,
                    email,
                    doctors,
                    clients);

            VetClinicInfo updatedVetClinicInfo =
                    vetClinicMapper.toModelFromDto(updatedVetClinicDto);

            var updatedVetClinicEntity =
                    vetClinicRepository.save(vetClinicMapper.toEntityFromModel((VetClinic) updatedVetClinicInfo));
            return vetClinicMapper.toDtoFromModel(vetClinicMapper.toModelFromEntity(updatedVetClinicEntity));
        } catch (Exception e) {
            throw new PetDoctorNotFoundException(vetClinicDto.getId().toString());
        }
    }

    @Override
    public void deleteVetClinicById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }
        try {
            vetClinicRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }

    public AppointmentInfo bookAppointment(AppointmentInfo appointment) {

        if (appointment == null) {
            throw new PetDoctorNullException("Appointment is null! :(");
        }

        if (!(appointment.getVetClinic() instanceof VetClinicInterface vetClinic)) {
            throw new PetDoctorValidationException("Incorrect type of vetClinic was taken");
        }

//        TODO: add mapping with @Entities

        return vetClinic.bookAppointment(appointment);
    }

}
