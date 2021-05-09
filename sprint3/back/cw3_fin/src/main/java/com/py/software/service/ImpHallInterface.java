package com.py.software.service;

import com.py.software.entity.Hall;
import com.py.software.entity.Schedule;
import com.py.software.repository.HallRepository;
import com.py.software.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class ImpHallInterface implements HallInterface{
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Hall findHallById(Long hall_id) {
        return hallRepository.findById(hall_id).get();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Hall> findHallByCinema(Long cinema_id) {
        return hallRepository.findHallByCinemaId(cinema_id);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public Hall findHallByScheduleId(Long schedule_id) {
        Schedule schedule = scheduleRepository.findById(schedule_id).get();
        return hallRepository.findById(schedule.getHall_id()).get();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void addHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void updateHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void deleteHall(Long hall_id) {
        hallRepository.deleteById(hall_id);
    }
}
