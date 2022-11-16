package com.petdoctor.domain.service.impl;

import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.data.repository.DoctorRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.service.DoctorService;
import com.petdoctor.domain.tool.exception.PetDoctorNotFoundException;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final Mapper<DoctorEntity, Doctor, DoctorDto> doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, Mapper<DoctorEntity, Doctor, DoctorDto> doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorDto> findAllDoctor() {
        List<DoctorInfo> doctorInfos = doctorRepository
                .findAll()
                .stream()
                .map(doctorEntity -> (DoctorInfo) doctorMapper.toModelFromEntity(doctorEntity))
                .toList();

        return doctorInfos
                .stream()
                .map(a -> doctorMapper.toDtoFromModel((Doctor) a))
                .toList();
    }

    @Override
    public DoctorDto getDoctorById(Long id) {

        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }

        try {
            DoctorInfo doctorInfo = doctorMapper.toModelFromEntity(doctorRepository.getReferenceById(id));
            return doctorMapper.toDtoFromModel((Doctor) doctorInfo);
        } catch (EntityNotFoundException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }

    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        if (doctorDto == null) {
            throw new PetDoctorNullException("the doctorDto is null!");
        }

        DoctorInfo doctorInfo = doctorMapper.toModelFromDto(doctorDto);
        var doctorEntity =
                doctorRepository.save(doctorMapper.toEntityFromModel((Doctor) doctorInfo));
        return doctorMapper.toDtoFromModel(doctorMapper.toModelFromEntity(doctorEntity));
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        if (doctorDto == null) {
            throw new PetDoctorNullException("the doctorDto is null!");
        }

        try {
            DoctorDto oldDoctor = this.getDoctorById(doctorDto.getId());
            String name = oldDoctor.getName();
            String surname = oldDoctor.getSurname();
            String email = oldDoctor.getEmail();
            Integer doctorOffice = oldDoctor.getDoctorOffice();
            ArrayList<AppointmentDto> appointmentDtos = oldDoctor.getAppointments();

            if (doctorDto.getName() != null) {
                name = doctorDto.getName();
            }

            if (doctorDto.getSurname() != null) {
                surname = doctorDto.getSurname();
            }

            if (doctorDto.getEmail() != null) {
                email = doctorDto.getEmail();
            }

            if (doctorDto.getDoctorOffice() != null) {
                doctorOffice = doctorDto.getDoctorOffice();
            }

            if (doctorDto.getAppointments() != null) {
                appointmentDtos = doctorDto.getAppointments();
            }

            var updatedDoctorDto = new DoctorDto(oldDoctor.getId(),
                    name,
                    surname,
                    email,
                    doctorOffice,
                    appointmentDtos);

            DoctorInfo updatedDoctorInfo =
                    doctorMapper.toModelFromDto(updatedDoctorDto);

            var updatedDoctorEntity =
                    doctorRepository.save(doctorMapper.toEntityFromModel((Doctor) updatedDoctorInfo));
            return doctorMapper.toDtoFromModel(doctorMapper.toModelFromEntity(updatedDoctorEntity));
        } catch (Exception e) {
            throw new PetDoctorNotFoundException(doctorDto.getId().toString());
        }
    }

    @Override
    public void deleteDoctorById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }
        try {
            doctorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }
}
