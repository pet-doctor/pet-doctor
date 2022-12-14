package com.petdoctor.domain.service.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.VetClinicEntity;
import com.petdoctor.data.repository.VetClinicRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.model.vet.clinic.VetClinicInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInterface;
import com.petdoctor.domain.service.VetClinicService;
import com.petdoctor.domain.tool.exception.PetDoctorNotFoundException;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import com.petdoctor.domain.tool.exception.PetDoctorValidationException;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VetClinicServiceImpl implements VetClinicService {
    private final VetClinicRepository vetClinicRepository;
    private final Mapper<VetClinicEntity, VetClinic, VetClinicDto> vetClinicMapper;
    private final Mapper<AppointmentEntity, Appointment, AppointmentDto> appointmentMapper;

    @Autowired
    public VetClinicServiceImpl(VetClinicRepository vetClinicRepository,
                                Mapper<VetClinicEntity, VetClinic, VetClinicDto> vetClinicMapper,
                                Mapper<AppointmentEntity, Appointment, AppointmentDto> appointmentMapper) {
        this.vetClinicRepository = vetClinicRepository;
        this.vetClinicMapper = vetClinicMapper;
        this.appointmentMapper = appointmentMapper;
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
            ArrayList<ClientDto> clients = oldVetClinic.getClients();
            ArrayList<DoctorDto> doctors = oldVetClinic.getDoctors();

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

    @Override
    public AppointmentDto closeAppointment(AppointmentDto appointmentDto) {
        AppointmentInfo appointmentInfo = appointmentMapper.toModelFromDto(appointmentDto);

        if (appointmentInfo == null) {
            throw new PetDoctorNullException("AppointmentInfo is null!");
        }

        if (!(appointmentInfo.getVetClinic() instanceof VetClinicInterface)) {
            throw new PetDoctorValidationException("Incorrect type of AppointmentInfo was taken");
        }

        if (appointmentInfo.getVetClinic() == null) {
            throw new PetDoctorNullException("Related vetClinic isn't exist");
        }

        return appointmentMapper.toDtoFromModel((Appointment) ((VetClinicInterface) appointmentInfo.getVetClinic()).closeAppointment(appointmentInfo));
    }

    @Override
    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) {
        AppointmentInfo appointmentInfo = appointmentMapper.toModelFromDto(appointmentDto);

        if (appointmentInfo == null) {
            throw new PetDoctorNullException("AppointmentInfo is null!");
        }

        if (!(appointmentInfo.getVetClinic() instanceof VetClinicInterface)) {
            throw new PetDoctorValidationException("Incorrect type of AppointmentInfo was taken");
        }

        if (appointmentInfo.getVetClinic() == null) {
            throw new PetDoctorNullException("Related vetClinic isn't exist");
        }

        VetClinicInterface vetClinicInterface = ((VetClinicInterface) appointmentInfo.getVetClinic());
        return appointmentMapper.toDtoFromModel((Appointment) vetClinicInterface.bookAppointment(appointmentInfo));
    }
}