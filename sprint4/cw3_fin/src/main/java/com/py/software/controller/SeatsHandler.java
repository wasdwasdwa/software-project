package com.py.software.controller;

import com.py.software.entity.Seat;
import com.py.software.service.ImpSeatInterface;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatsHandler {
    @Autowired
    private ImpSeatInterface impSeatInterface;

    @GetMapping("")
    public List<Seat> getSeats(@RequestParam(value = "did")Long schedule_id){
        return impSeatInterface.findSeatsByScheduleId(schedule_id);
    }

    @GetMapping("/changeSelected")
    public Seat changeSelected(@RequestParam(value = "sid")Long seat_id){
        impSeatInterface.updateSelected(seat_id);
        return impSeatInterface.findById(seat_id);
    }

    @GetMapping("/seat_id")
    public Seat getSeat(@RequestParam(value = "sid")Long seat_id){
        return impSeatInterface.findById(seat_id);
    }
}
