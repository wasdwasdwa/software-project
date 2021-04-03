package com.py.software.repository;

import com.py.software.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall,Long> {
    @Query(value="select * from hall where cinema_id = ?1", nativeQuery=true)
    List<Hall> findHallByCinemaId(Long cinema_id);
}
