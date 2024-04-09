package com.ski.skiresort.service;

import com.ski.skiresort.domain.entity.Coach;
import com.ski.skiresort.dto.CoachDto;
import com.ski.skiresort.dto.CoachResponse;

import java.util.List;

public interface CoachService {

    CoachDto createCoach(CoachDto coachDto);

    CoachResponse getAllCoach(int pageNo, int pageSize);

    CoachDto getCoachById(Long id);

    CoachDto updateCoach(CoachDto coachDto, Long id);

    void deleteCoachById(Long id);
}

