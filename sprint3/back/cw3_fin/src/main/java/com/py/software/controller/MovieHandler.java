package com.py.software.controller;

import com.py.software.entity.Cinema;
import com.py.software.entity.Movie;
import com.py.software.entity.Schedule;
import com.py.software.service.ImpCinemaInterface;
import com.py.software.service.ImpMovieInterface;
import com.py.software.service.ImpScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/movies")
public class MovieHandler {
    @Autowired
    private ImpCinemaInterface impCinemaInterface;

    @Autowired
    private ImpMovieInterface impMovieInterface;

    @Autowired
    private ImpScheduleInterface impScheduleInterface;

    @GetMapping("/cinema_id")
    public List<Movie> findSomeMovies(@RequestParam(value = "cid")Long id){
        return impScheduleInterface.findMoviesBySchedule(id);
    }

    @GetMapping("/movie_id")
    public Movie useIdFindCinema(@RequestParam(value = "mid") Long id){
        return impMovieInterface.findMovieById(id);
    }

    @GetMapping("")
    public List<Movie> getMovies(){
        return impMovieInterface.findAllMovie();
    }

    @GetMapping("/schedule_id")
    public Movie findMovieByScheduleId(@RequestParam(value = "sid")Long schedule_id){
        return impScheduleInterface.findSingleMovie(schedule_id);
    }


    @GetMapping("/movie_name/{nebulous_name}")
    public List<Movie> findCinemaByName(@PathVariable("nebulous_name") String name){
        return impMovieInterface.findMoviesLikeCnName(name);
    }
}
