package com.py.software.service;

import com.py.software.entity.Seat;
import com.py.software.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ImpSeatInterface implements SeatIntereface{
    @Autowired
    private SeatRepository seatRepository;


    @Override
    public Seat findById(Long seat_id) {
        return seatRepository.findSeatsById(seat_id);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Seat> findSeatsByScheduleId(Long schedule_id) {
        return seatRepository.findSeatsByScheduleId(schedule_id);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void updateSelected(Long seat_id) {
        if(seatRepository.findById(seat_id).get().getIsAvailable()==0){
            return;
        }
        if(seatRepository.findById(seat_id).get().getIsSelected()==1){
            seatRepository.updateunSelected(seat_id);
        }else{
            seatRepository.updateSelected(seat_id);
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void seatUnavailable(Seat seat) {
        Long py = seat.getSchedule_id();
        seatRepository.updateavailable(py);
    }
}
