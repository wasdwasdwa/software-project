package com.py.software.controller;

import com.py.software.entity.Cinema;
import com.py.software.service.ImpCinemaInterface;
import com.py.software.service.ImpScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaHandler {
    @Autowired
    private ImpCinemaInterface impCinemaInterface;

    @Autowired
    private ImpScheduleInterface impScheduleInterface;

    @GetMapping("/schedule_id")
    public Cinema getCinema(@RequestParam(value = "sid")Long schedule_id){
        return impScheduleInterface.findSingleCinema(schedule_id);
    }

    @GetMapping("")
    public List<Cinema> getAllCinema(){
        return impCinemaInterface.findAllCinema();
    }

    @GetMapping("/cinema_id")
    public Cinema getSingle(@RequestParam(value="cid")int cid){
        return impCinemaInterface.findCinemaById(cid);
    }

    @GetMapping("/movieCinemas")
    public List<Cinema> getCinema(@RequestParam(value = "mid") int mid){
        return impCinemaInterface.findCinemaByMovieId(mid);
    }


}
