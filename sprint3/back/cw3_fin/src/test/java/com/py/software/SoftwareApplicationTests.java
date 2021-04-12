package com.py.software;

import com.py.software.repository.CinemaRepository;
import com.py.software.repository.OrderinfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class SoftwareApplicationTests {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private OrderinfoRepository orderinfoRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void findAlLCinema(){
        System.out.println(cinemaRepository.findAll());
    }

    @Test
    void findCinemaByName(){
        System.out.println(cinemaRepository.findCinemaByCinemaNameLike("奇幻"));
    }

    @Test
    void testOrderRepository(){
        Pageable pageable = PageRequest.of(1,2);
        System.out.println(orderinfoRepository.findOrderByUserId(3L, pageable ));
    }

}
