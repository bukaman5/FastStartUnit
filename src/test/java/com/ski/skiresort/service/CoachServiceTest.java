package com.ski.skiresort.service;

import com.ski.skiresort.dao.CoachRepository;
import com.ski.skiresort.domain.entity.Coach;
import com.ski.skiresort.dto.CoachDto;
import com.ski.skiresort.dto.CoachResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoachServiceTest {

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private CoachServiceImpl coachService;

    @Test
    public void CoachService_CreateCoach_ReturnsCoachDto() {
        Coach coach = Coach.builder()
                .fullName("Misha")
                .category("Ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();
        CoachDto coachDto = CoachDto.builder()
                .fullName("Misha")
                .category("Ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();

        when(coachRepository.save(Mockito.any(Coach.class))).thenReturn(coach);
        CoachDto savedPokemon = coachService.createCoach(coachDto);
        Assertions.assertThat(savedPokemon).isNotNull();
    }
    @Test
    public void CoachService_GetAllCoach_ReturnsResponseDto() {
        Page<Coach> coaches = Mockito.mock(Page.class);

        when(coachRepository.findAll(Mockito.any(Pageable.class))).thenReturn(coaches);

        CoachResponse coachResponse = coachService.getAllCoach(1,10);

        Assertions.assertThat(coachResponse).isNotNull();
    }
    @Test
    public void CoachService_FindById_ReturnCoachDto() {
        Long coachId = 1L;
        Coach coach = Coach.builder()
                .id(1L)
                .fullName("Misha")
                .category("Ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();
        when(coachRepository.findById(coachId)).thenReturn(Optional.ofNullable(coach));

        CoachDto coachDto = coachService.getCoachById(coachId);

        Assertions.assertThat(coachDto).isNotNull();
    }

    @Test
    public void CoachService_UpdateCoach_ReturnCoachDto() {
        Long coachId = 1L;
        Coach coach = Coach.builder()
                .id(1L)
                .fullName("Misha")
                .category("Ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();
        CoachDto coachDto = CoachDto.builder()
                .id(1L)
                .fullName("Misha")
                .category("Ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();

        when(coachRepository.findById(coachId)).thenReturn(Optional.ofNullable(coach));
        when(coachRepository.save(coach)).thenReturn(coach);
        CoachDto updateReturn = coachService.updateCoach(coachDto, coachId);
        Assertions.assertThat(updateReturn).isNotNull();
    }

    @Test
    public void CoachService_DeleteCoachById_ReturnVoid() {
        Long coachId = 1L;
        Coach coach = Coach.builder()
                .id(1L)
                .fullName("Misha")
                .category("Ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();

        when(coachRepository.findById(coachId)).thenReturn(Optional.ofNullable(coach));
        doNothing().when(coachRepository).delete(coach);

        assertAll(() -> coachService.deleteCoachById(coachId));
    }
}
