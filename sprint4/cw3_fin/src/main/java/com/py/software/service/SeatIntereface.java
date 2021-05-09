package com.py.software.service;

import com.py.software.entity.Seat;

import java.util.List;

public interface SeatIntereface {
    Seat findById(Long seat_id);
    List<Seat> findSeatsByScheduleId(Long schedule_id);
    void updateSelected(Long seat_id);
    void seatUnavailable(Seat seat);
}
