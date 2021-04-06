package com.py.software.service;

import com.py.software.entity.Cinema;
import com.py.software.entity.Hall;
import com.py.software.entity.Movie;
import com.py.software.entity.Schedule;
import com.py.software.repository.CinemaRepository;
import com.py.software.repository.HallRepository;
import com.py.software.repository.MovieRepository;
import com.py.software.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ImpScheduleInterface implements ScheduleInterface{
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HallRepository hallRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Schedule findScheduleById(Long schedule_id) {
        return scheduleRepository.findById(schedule_id).get();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void deleteSchedule(Long schedule_id) {
        scheduleRepository.deleteById(schedule_id);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void addScheduleRemain(Long schedule_id) {
        scheduleRepository.AddScheduleRemain(schedule_id);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void delScheduleRemain(Long schedule_id) {
        Schedule schedule = scheduleRepository.findById(schedule_id).get();
        if(schedule.getSchedule_remain()>0){
            schedule.setSchedule_remain(schedule.getSchedule_remain()-1);
            scheduleRepository.save(schedule);
        }
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<Schedule> findScheduleByMovieName(Integer page, Integer limit, String movie_name) {
        Pageable pageable = PageRequest.of(page-1,limit);
        Movie movie = movieRepository.findMovieByMovieCnName(movie_name);
        return scheduleRepository.findSchedulesByMovieId(movie.getMovie_id(),pageable);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<Schedule> findOffScheduleByMovieName(Integer page, Integer limit, String movie_name) {
        Pageable pageable = PageRequest.of(page-1,limit);
        Movie movie = movieRepository.findMovieByExactName(movie_name);
        return scheduleRepository.findOffSchedulesByMovieId(movie.getMovie_id(),pageable);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<Schedule> findAllSchedule(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return scheduleRepository.findAll(pageable);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<Schedule> findAllScheduleByState(Integer page, Integer limit, Integer schedule_state) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return scheduleRepository.findScheduleByState(schedule_state,pageable);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Schedule> findScheduleByCinemaAndMovie(Long cinema_id, Long movie_id) {
        List<Hall> hallList = hallRepository.findHallByCinemaId(cinema_id);
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for(Hall hall:hallList)
            scheduleList.addAll(scheduleRepository.findScheduleByMovieIdAndHallId(movie_id, hall.getHall_id()));
        return scheduleList;
    }

//    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//    @Override
//    public List<Schedule> findScheduleByCinemaIdAndMovieId(Long cinema_id, Long movie_id) {
//        return null;
//    }
}
