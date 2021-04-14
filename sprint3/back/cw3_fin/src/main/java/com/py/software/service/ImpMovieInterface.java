package com.py.software.service;

import com.py.software.entity.Movie;
import com.py.software.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class ImpMovieInterface implements MovieInterface{

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Movie findMovieById(Long movie_id) {
        return movieRepository.findById(movie_id).get();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Movie findMovieByName(String movie_cn_name) {
        return movieRepository.findMovieByMovieCnName(movie_cn_name);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void deleteMovie(Long movie_id) {
        movieRepository.deleteById(movie_id);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void delCommentCount(Long movie_id) {
        Movie movie  = movieRepository.findById(movie_id).get();
        Long num = movie.getMovie_comment_count();
        movie.setMovie_comment_count(--num);
        movieRepository.save(movie);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void addCommentCount(Long movie_id) {
        Movie movie  = movieRepository.findById(movie_id).get();
        Long num = movie.getMovie_comment_count();
        movie.setMovie_comment_count(++num);
        movieRepository.save(movie);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void changeMovieBoxOffice(float price, Long movie_id) {
        Movie movie = movieRepository.findById(movie_id).get();
        float temp = movie.getMovie_box_office();
        movie.setMovie_box_office(temp+price);
        movieRepository.save(movie);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> findAllMovies(int movie_state) {
        return movieRepository.findMovieByMovieState(movie_state);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> findMoviesLikeCnName(String cn_name) {
        return movieRepository.findMovieByCnName(cn_name);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> findMoviesLikeFnName(String fn_name){
        return movieRepository.findMovieByFnName(fn_name);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> findMovieLikeType(String type) {
        return movieRepository.findMovieByType(type);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> sortMovieByDate(String sort) {
        return movieRepository.findMovieByMovieReleaseDate(sort);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> sortMovieByCount(String sort) {
        return movieRepository.findMovieByCommentList(sort);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> sortMovieByScore(String sort) {
        return movieRepository.findMovieByMovieScore(sort);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Movie> sortMovieByBoxOffice(String sort) {
        return movieRepository.findMovieByMovieBoxOffice(sort);
    }
}
