package com.ski.skiresort.service;

import com.ski.skiresort.domain.entity.Coach;

import java.util.List;

public interface CoachService {

    List<Coach> findAll();

    Coach save(Coach theCoach);

    void deleteById(long theId);

    Coach findById(long theId);

}

