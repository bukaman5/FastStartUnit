package com.ski.skiresort.service;

import com.ski.skiresort.dao.CoachRepository;

import com.ski.skiresort.domain.entity.Coach;

import com.ski.skiresort.dto.CoachDto;
import com.ski.skiresort.dto.CoachResponse;
import com.ski.skiresort.exeption.CoachNotFoundException;
import com.ski.skiresort.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;


    @Override
    public CoachDto createCoach(CoachDto coachDto) {
        Coach coach = new Coach();
        coach.setFullName(coachDto.getFullName());
        coach.setCategory(coachDto.getCategory());
        coach.setDateOfBirth(coachDto.getDateOfBirth());
        coach.setGender(coachDto.getGender());
        coach.setPhoto(coachDto.getPhoto());

        Coach newCoach = coachRepository.save(coach);

        CoachDto coachResponse = new CoachDto();
        coachResponse.setId(newCoach.getId());
        coachResponse.setFullName(newCoach.getFullName());
        coachResponse.setCategory(newCoach.getCategory());
        coachResponse.setDateOfBirth(newCoach.getDateOfBirth());
        coachResponse.setGender(newCoach.getGender());
        coachResponse.setPhoto(newCoach.getPhoto());
        return coachResponse;

    }

    @Override
    public CoachResponse getAllCoach(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Coach> coaches = coachRepository.findAll(pageable);

        List<Coach> coachList = coaches.getContent();

        List<CoachDto> content = coachList.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        CoachResponse coachResponse = new CoachResponse();
        coachResponse.setContent(content);
        coachResponse.setPageNo(coaches.getNumber());
        coachResponse.setPageSize(coaches.getSize());
        coachResponse.setTotalElements(coaches.getTotalElements());
        coachResponse.setTotalPages(coaches.getTotalPages());
        coachResponse.setLast(coaches.isLast());

        return coachResponse;

    }

    @Override
    public CoachDto getCoachById(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException("Coach could not be found"));
        return mapToDto(coach);
    }

    @Override
    public CoachDto updateCoach(CoachDto coachDto, Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException("Coach could not be updated"));

        coach.setFullName(coachDto.getFullName());
        coach.setCategory(coachDto.getCategory());
        coach.setGender(coachDto.getGender());
        coach.setDateOfBirth(coachDto.getDateOfBirth());
        coach.setPhoto(coachDto.getPhoto());

        Coach updatedCoach = coachRepository.save(coach);
        return mapToDto(updatedCoach);
    }

    @Override
    public void deleteCoachById(Long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException("Coach could not be delete"));
        coachRepository.delete(coach);
    }
    private CoachDto mapToDto(Coach coach) {
        CoachDto coachDto = new CoachDto();

        coachDto.setId(coach.getId());
        coachDto.setFullName(coach.getFullName());
        coachDto.setCategory(coach.getCategory());
        coachDto.setDateOfBirth(coach.getDateOfBirth());
        coachDto.setGender(coach.getGender());
        coachDto.setPhoto(coach.getPhoto());
        return coachDto;
    }
    private Coach mapToEntity(CoachDto coachDto) {
        Coach coach = new Coach();
        coach.setFullName(coachDto.getFullName());
        coach.setCategory(coachDto.getCategory());
        coach.setDateOfBirth(coachDto.getDateOfBirth());
        coach.setGender(coachDto.getGender());
        coach.setPhoto(coachDto.getPhoto());
        return coach;
    }

}
