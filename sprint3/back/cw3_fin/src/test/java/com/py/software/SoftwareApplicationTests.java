package com.py.software;

import com.py.software.repository.CinemaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoftwareApplicationTests {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void findAlLCinema(){
        System.out.println(cinemaRepository.findAll());
    }

    @Test
    void findCinemaByName(){
        System.out.println(cinemaRepository.findCinemaByCinemanameLike("奇幻"));
    }
}
