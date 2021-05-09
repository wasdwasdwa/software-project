package com.py.software.service;

import com.py.software.entity.Cinema;
import com.py.software.entity.Movie;
import com.py.software.entity.Schedule;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScheduleInterface {
    Schedule findScheduleById(Long schedule_id);
    void addSchedule(Schedule schedule);
    void updateSchedule(Schedule schedule);
    void deleteSchedule(Long schedule_id);
    void addScheduleRemain(Long schedule_id);
    void delScheduleRemain(Long schedule_id);
    Movie findSingleMovie(Long schedule_id);
    Cinema findSingleCinema(Long schedule_id);
    Page<Schedule> findScheduleByMovieName(Integer page,Integer limit,String movie_name);
    Page<Schedule> findOffScheduleByMovieName(Integer page,Integer limit,String movie_name);
    Page<Schedule> findAllSchedule(Integer page, Integer limit);
    Page<Schedule> findAllScheduleByState(Integer page,Integer limit,Integer schedule_state);
    List<Schedule> findScheduleByCinemaAndMovie(Long cinema_id, Long movie_id);
    List<Movie> findMoviesBySchedule(Long hall_id);
//    List<Schedule> findScheduleByCinemaIdAndMovieId(Long cinema_id,Long movie_id);
}
