package com.py.software.repository;

import com.py.software.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema,Long> {
    @Query(value="select * from cinema where cinema_name like concat('%',?1,'%') " ,nativeQuery=true)
    List<Cinema> findCinemaByCinemanameLike(String cinema_name);
}
