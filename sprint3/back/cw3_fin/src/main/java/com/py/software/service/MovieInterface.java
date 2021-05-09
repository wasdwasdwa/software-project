package com.py.software.service;

import com.py.software.entity.Movie;

import java.util.List;

public interface MovieInterface {
    Movie findMovieById(Long movie_id);
    Movie findMovieByName(String movie_cn_name);
    void addMovie(Movie movie);
    void deleteMovie(Long movie_id);
    void updateMovie(Movie movie);
    void delCommentCount(Long movie_id);
    void addCommentCount(Long movie_id);
    void changeMovieBoxOffice(float price,Long movie_id);
    List<Movie> findAllMovies(int movie_state);
    List<Movie> findAllMovie();
    List<Movie> findMoviesLikeCnName(String cn_name);
    List<Movie> findMoviesLikeFnName(String fn_name);
    List<Movie> findMovieLikeType(String type);

    List<Movie> sortMovieByDate(String sort);
    List<Movie> sortMovieByCount(String sort);
    List<Movie> sortMovieByScore(String sort);
    List<Movie> sortMovieByBoxOffice(String sort);
}
