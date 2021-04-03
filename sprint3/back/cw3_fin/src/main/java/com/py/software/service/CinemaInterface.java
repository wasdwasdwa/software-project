package com.py.software.service;

import com.py.software.entity.Cinema;

import java.util.List;

public interface CinemaInterface {
    Cinema findCinemaById(long cinema_id);
    String addCinema(Cinema cinema);
    void deleteCinema(long cinema_id);
    List<Cinema> findAllCinema();
    List<Cinema> findCinemaLikeName(String cinema_name);
    List<Cinema> findCinemaByMovieId(long movie_id);
}
