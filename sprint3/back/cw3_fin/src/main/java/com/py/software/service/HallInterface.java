package com.py.software.service;

import com.py.software.entity.Hall;

import java.util.List;

public interface HallInterface {
    Hall findHallById(Long hall_id);
    List<Hall> findHallByCinema(Long cinema_id);
    Hall findHallByScheduleId(Long schedule_id);
    void addHall(Hall hall);
    void updateHall(Hall hall);
    void deleteHall(Long hall_id);
}
