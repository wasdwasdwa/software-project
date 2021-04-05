package com.py.software.repository;

import com.py.software.entity.Orderinfo;
import com.py.software.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query(value="select * from schedule where hall_id=?1", nativeQuery=true)
    List<Schedule> findScheduleByHallId(Long hall_id);
    @Query(value="select * from schedule where movie_id=?1", nativeQuery=true)
    List<Schedule> findScheduleByMovieId(Long movie_id);
    @Modifying
    @Query(value = "update schedule set schedule_remain = schedule_remain + 1 where schedule_id = ?1",nativeQuery = true)
    void AddScheduleRemain(Long schedule_id);
    @Query("from Schedule s where s.movie_id = ?1")
    Page<Schedule> findSchedulesByMovieId(Long movie_id,Pageable pageable);
    @Query("from Schedule s where s.movie_id = ?1 and s.schedule_state=0")
    Page<Schedule> findOffSchedulesByMovieId(Long movie_id,Pageable pageable);
    @Query("from Schedule s where s.schedule_state=?1")
    Page<Schedule> findScheduleByState(int state,Pageable pageable);
    @Query(value = "select * from schedule where movie_id=?1 and hall_id = ?2",nativeQuery = true)
    List<Schedule> findScheduleByMovieIdAndHallId(Long movie_id,Long hall_id);
}
