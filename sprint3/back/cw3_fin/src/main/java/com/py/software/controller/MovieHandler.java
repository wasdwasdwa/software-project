package com.py.software.controller;

import com.py.software.entity.Cinema;
import com.py.software.entity.Movie;
import com.py.software.entity.Schedule;
import com.py.software.service.ImpCinemaInterface;
import com.py.software.service.ImpMovieInterface;
import com.py.software.service.ImpScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/cinema")
public class MovieHandler {

    private ImpCinemaInterface impCinemaInterface;


    private ImpScheduleInterface impScheduleInterface;


    private ImpMovieInterface impMovieInterface;

    @GetMapping("/movie_id/{id}")
    public List<Cinema> useIdFindCinema(@PathVariable("id") Long id){
        return impCinemaInterface.findCinemaByMovieId(id);
    }

    @GetMapping("/findschedule/{movie_id}/{cinema_id}")
    public Map<String, Object> findSchedule(@PathVariable("movie_id") Long movie_id, @PathVariable("cinema_id") Long cinema_id) throws ParseException {
        List<Schedule> scheduleList = impScheduleInterface.findScheduleByCinemaAndMovie(cinema_id,movie_id);
        Map<String,Object> result = new HashMap<>();
        List<Schedule> one = new ArrayList<>();
        List<Schedule> two = new ArrayList<>();
        List<Schedule> three = new ArrayList<>();
        List<Schedule> four = new ArrayList<>();
        SimpleDateFormat py_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String cur_y = "2019-07-21 ";
        SimpleDateFormat py_2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat py_3 = new SimpleDateFormat("yyyy-MM-dd");
        Date cur = new Date();
        String cur_s = py_2.format(cur);
        Date make = py_1.parse(cur_y+cur_s);
        for(Schedule schedule:scheduleList){
            Date newtime = py_1.parse(schedule.getSchedule_start_time());
            schedule.setNewTime(newtime);
            if(newtime.after(make)){
                int days = (int) ((py_3.parse(py_3.format(newtime)).getTime() - py_3.parse(cur_y).getTime()) / (1000*3600*24));
                switch (days){
                    case 0:
                        one.add(schedule);
                        break;
                    case 1:
                        two.add(schedule);
                        break;
                    case 2:
                        three.add(schedule);
                        break;
                    case 3:
                        four.add(schedule);
                        break;
                    default:
                        break;
                }
            }
        }
        result.put("today",one);
        result.put("today+1",two);
        result.put("today+2",three);
        result.put("today+3",four);
        return result;
    }

    @GetMapping("/movie_name/{nebulous_name}")
    public List<Movie> findCinemaByName(@PathVariable("nebulous_name") String name){
        return impMovieInterface.findMoviesLikeCnName(name);
    }
}
