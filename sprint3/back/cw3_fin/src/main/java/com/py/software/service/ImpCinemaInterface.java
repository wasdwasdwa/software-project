package com.py.software.service;

import com.py.software.entity.Cinema;
import com.py.software.entity.Hall;
import com.py.software.entity.Schedule;
import com.py.software.repository.CinemaRepository;
import com.py.software.repository.HallRepository;
import com.py.software.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ImpCinemaInterface implements CinemaInterface{
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Cinema findCinemaById(long cinema_id) {
        Cinema cinema = cinemaRepository.findById(cinema_id).get();
        List<Hall> hallList = hallRepository.findHallByCinemaId(cinema_id);
        for(Hall hall: hallList){
            List<Schedule> scheduleList = scheduleRepository.findScheduleByHallId(hall.getHall_id());
            hall.setScheduleList(scheduleList);
        }
        cinema.setHallList(hallList);
        return cinema;
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public String addCinema(Cinema cinema) {
        Cinema cinema1 = cinemaRepository.save(cinema);
        if(cinema1!=null){
            return "success";
        }
        return "fail";
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void deleteCinema(long cinema_id) {
        cinemaRepository.deleteById(cinema_id);;
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Cinema> findAllCinema() {
        return cinemaRepository.findAll();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Cinema> findCinemaLikeName(String cinema_name) {
        return cinemaRepository.findCinemaByCinemanameLike(cinema_name);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Cinema> findCinemaByMovieId(long movie_id) {
        ArrayList<Cinema> result = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findScheduleByMovieId(movie_id);
        for(Schedule schedule:scheduleList){
            Hall hall = hallRepository.findById(schedule.getHall_id()).get();
            Cinema cinema = cinemaRepository.findById(hall.getCinema_id()).get();
            result.add(cinema);
        }
        return result;
    }
}
