package com.ski.skiresort.service;

import com.ski.skiresort.dao.CoachRepository;

import com.ski.skiresort.domain.entity.Coach;

import com.ski.skiresort.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;


    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Coach save(Coach theCoach) {
        return coachRepository.save(theCoach);
    }

    @Override
    public void deleteById(long theId) {

        Optional<Coach> result = this.coachRepository.findById(theId);
        if (result.isPresent()) {
            this.coachRepository.deleteById(result.get().getId());
        } else {
            throw new ResourceNotFoundException("Didnt find coach with id" + theId);
        }


    }

    @Override
    public Coach findById(long theId) {
        Optional<Coach> result = this.coachRepository.findById(theId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Didnt find coach with id" + theId);
        }
    }
}
