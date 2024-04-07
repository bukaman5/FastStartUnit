package com.ski.skiresort.dao;

import com.ski.skiresort.domain.entity.Coach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CoachRepositoryTest {

    @Autowired
    private CoachRepository coachRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCoachTest(){

        Coach coach = Coach.builder()
                .fullName("Misha")
                .category("ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();

        coachRepository.save(coach);

        Assertions.assertThat(coach.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getCoachTest(){

        Coach coach = coachRepository.findById(1L).get();
        Assertions.assertThat(coach.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfCoachesTest(){

        List<Coach> coaches = coachRepository.findAll();

        Assertions.assertThat(coaches.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCoachTest(){

        Coach coach = Coach.builder()
                .fullName("Misha")
                .category("ski")
                .dateOfBirth("01.01.2000")
                .gender("Male")
                .photo("qwertyuiop")
                .build();

        coachRepository.save(coach);

        coach.setFullName("MishaChanged");

        Coach coachUpdated =  coachRepository.save(coach);

        Assertions.assertThat(coachUpdated.getFullName()).isEqualTo("MishaChanged");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        Coach coach = coachRepository.findById(1L).get();

        coachRepository.delete(coach);

        //coachRepository.deleteById(1L);

        Coach coach1 = null;

        Optional<Coach> optionalCoach = coachRepository.findByFullName("Misha");

        if(optionalCoach.isPresent()){
            coach1 = optionalCoach.get();
        }

        Assertions.assertThat(coach1).isNull();
    }

}
