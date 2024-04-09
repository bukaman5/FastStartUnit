package com.ski.skiresort.controller;


import com.ski.skiresort.domain.entity.Coach;
import com.ski.skiresort.dto.CoachDto;
import com.ski.skiresort.dto.CoachResponse;
import com.ski.skiresort.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoachController {

    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService theCoachService) {
        coachService = theCoachService;
    }

    @GetMapping("/coach")
    public ResponseEntity<CoachResponse> findAll(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
            return new ResponseEntity<>(coachService.getAllCoach(pageNo, pageSize), HttpStatus.OK);
}
    @GetMapping("/coaches/{theId}")
    public ResponseEntity<CoachDto> findById(@PathVariable long theId) {
        return ResponseEntity.ok(coachService.getCoachById(theId));
    }

    @PostMapping("/coaches")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CoachDto> addCoach(@RequestBody CoachDto coachDto) {
        return new ResponseEntity<>(coachService.createCoach(coachDto), HttpStatus.CREATED);
    }

    @PutMapping("/coaches/{id}/update")
    public ResponseEntity<CoachDto> updateCoach(@RequestBody CoachDto coachDto, @PathVariable("id") long coachID) {
        CoachDto response = coachService.updateCoach(coachDto, coachID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("coaches/{id}/delete")
    public ResponseEntity<String> deleteCoach(@PathVariable("id") long coachId) {
        coachService.deleteCoachById(coachId);
        return new ResponseEntity<>("Coach deleted", HttpStatus.OK);
    }


}