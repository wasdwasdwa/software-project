package com.py.software.repository;

import com.py.software.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query(value="select * from movie where movie_cn_name = ?1",nativeQuery = true)
    Movie findMovieByMovieCnName(String name);
    @Query(value="select * from movie where movie_state = ?1",nativeQuery = true)
    List<Movie> findMovieByMovieState(Integer movie_state);
    @Query(value = "select * from movie where movie_cn_name like concat('%',?1,'%') ",nativeQuery = true)
    List<Movie> findMovieByCnName(String cn_name);
    @Query(value = "select * from movie where movie_fg_name like concat('%',?1,'%') ",nativeQuery = true)
    List<Movie> findMovieByFnName(String fg_name);
    @Query(value = "select * from movie where movie_type like concat('%',?1,'%') ",nativeQuery = true)
    List<Movie> findMovieByType(String type);
    @Query(value = "select * from movie order by movie_release_date ?1",nativeQuery = true)
    List<Movie> findMovieByMovieReleaseDate(String sort);
    @Query(value = "select * from movie order by movie_comment_count ?1",nativeQuery = true)
    List<Movie> findMovieByCommentList(String sort);
    @Query(value = "select * from movie order by movie_score ?1",nativeQuery = true)
    List<Movie> findMovieByMovieScore(String sort);
    @Query(value = "select * from movie order by movie_box_office ?1",nativeQuery = true)
    List<Movie> findMovieByMovieBoxOffice(String sort);
    @Query(value = "select u from movie where movie_cn_name = ?1",nativeQuery = true)
    Movie findMovieByExactName(String name);
}
