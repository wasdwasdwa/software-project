package com.py.software.repository;

import com.py.software.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query(value="select * from schedule where hall_id=?1", nativeQuery=true)
    List<Schedule> findScheduleByHallid(Long hall_id);
    @Query(value="select * from schedule where movie_id=?1", nativeQuery=true)
    List<Schedule> findScheduleByMovieid(Long movie_id);
}
