package com.petdoctor.domain.service;

import com.petdoctor.data.entity.VetClinicEntity;
import com.petdoctor.data.repository.VetClinicRepository;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.service.impl.VetClinicServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VetClinicServiceTest {

    @MockBean
    private VetClinicRepository vetClinicRepository;

    @Autowired
    private VetClinicServiceImpl vetClinicService;

    @Test
    public void saveVetClinic() {
        var vetClinicEntity = new VetClinicEntity(1L, "Kronva", "vet@gmail.com", new ArrayList<>(), new ArrayList<>());
        Mockito.when(vetClinicRepository.save(Mockito.any(VetClinicEntity.class))).thenReturn(vetClinicEntity);

        var expectedVetClinicId = 1L;
        var expectedVetClinicAddress = "Kronva";
        var expectedVetClinicEmail = "vet@gmail.com";

        var vetClinicDto = new VetClinicDto(1L, "Kronva", "vet@gmail.com", new ArrayList<>(), new ArrayList<>());
        var savedVetClinicDto = vetClinicService.saveVetClinic(vetClinicDto);

        assertEquals(expectedVetClinicId, savedVetClinicDto.getId());
        assertEquals(expectedVetClinicAddress, savedVetClinicDto.getAddress());
        assertEquals(expectedVetClinicEmail, savedVetClinicDto.getEmail());
    }

    @Test
    public void findAllVetClinic() {
        var vetClinicEntity1 = new VetClinicEntity(1L, "Vyazma1", "vet1@mail.ru", new ArrayList<>(), new ArrayList<>());
        var vetClinicEntity2 = new VetClinicEntity(2L, "Vyazma2", "vet2@mail.ru", new ArrayList<>(), new ArrayList<>());
        var vetClinicEntity3 = new VetClinicEntity(3L, "Vyazma3", "vet3@mail.ru", new ArrayList<>(), new ArrayList<>());

        var expectedVetClinicId1 = 1L;
        var expectedVetClinicAddress1 = "Vyazma1";
        var expectedVetClinicEmail1 = "vet1@mail.ru";


        var expectedVetClinicId2 = 2L;
        var expectedVetClinicAddress2 = "Vyazma2";
        var expectedVetClinicEmail2 = "vet2@mail.ru";


        var expectedVetClinicId3 = 3L;
        var expectedVetClinicAddress3 = "Vyazma3";
        var expectedVetClinicEmail3 = "vet3@mail.ru";

        Mockito.when(vetClinicRepository
                .findAll()).thenReturn(List.of(vetClinicEntity1, vetClinicEntity2, vetClinicEntity3));

        List<VetClinicDto> vetClinicDtos = vetClinicService.findAllVetClinic();

        assertEquals(expectedVetClinicId1, vetClinicDtos.get(0).getId());
        assertEquals(expectedVetClinicId2, vetClinicDtos.get(1).getId());
        assertEquals(expectedVetClinicId3, vetClinicDtos.get(2).getId());

        assertEquals(expectedVetClinicAddress1, vetClinicDtos.get(0).getAddress());
        assertEquals(expectedVetClinicAddress2, vetClinicDtos.get(1).getAddress());
        assertEquals(expectedVetClinicAddress3, vetClinicDtos.get(2).getAddress());

        assertEquals(expectedVetClinicEmail1, vetClinicDtos.get(0).getEmail());
        assertEquals(expectedVetClinicEmail2, vetClinicDtos.get(1).getEmail());
        assertEquals(expectedVetClinicEmail3, vetClinicDtos.get(2).getEmail());
    }

    @Test
    public void getVetClinicById() {
        var vetClinicEntity = new VetClinicEntity(1L, "Kronva", "vet@gmail.com", new ArrayList<>(), new ArrayList<>());

        var expectedVetClinicId = 1L;
        var expectedVetClinicAddress = "Kronva";
        var expectedVetClinicEmail = "vet@gmail.com";

        Mockito.when(vetClinicRepository.getReferenceById(1L)).thenReturn(vetClinicEntity);
        VetClinicDto vetClinicDto = vetClinicService.getVetClinicById(1L);

        assertEquals(expectedVetClinicId, vetClinicDto.getId());
        assertEquals(expectedVetClinicAddress, vetClinicDto.getAddress());
        assertEquals(expectedVetClinicEmail, vetClinicDto.getEmail());
    }

    @Test
    public void updateVetClinic() {
        var vetClinicEntity = new VetClinicEntity(1L, "Kronva", "vet@gmail.com", new ArrayList<>(), new ArrayList<>());
        Mockito.when(vetClinicRepository.getReferenceById(1L)).thenReturn(vetClinicEntity);

        var expectedVetClinic = new VetClinicEntity(1L, "Vyazma", "vet@gmail.com", new ArrayList<>(), new ArrayList<>());

        Mockito.when(vetClinicRepository.save(Mockito.any(VetClinicEntity.class))).thenReturn(expectedVetClinic);


        var newVetClinicDto = new VetClinicDto(1L, "Vyazma", null, new ArrayList<>(), new ArrayList<>());
        VetClinicDto vetClinicDto = vetClinicService.updateVetClinic(newVetClinicDto);

        assertEquals(expectedVetClinic.getId(), vetClinicDto.getId());
        assertEquals(expectedVetClinic.getAddress(), vetClinicDto.getAddress());
        assertEquals(expectedVetClinic.getEmail(), vetClinicDto.getEmail());
    }

    @Test
    public void deleteVetClinicById() {
        vetClinicRepository.deleteById(1L);
        Mockito.verify(vetClinicRepository, Mockito.times(1)).deleteById(1L);
    }
}