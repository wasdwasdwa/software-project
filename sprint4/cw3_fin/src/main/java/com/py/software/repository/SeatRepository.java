package com.py.software.repository;

import com.py.software.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    @Query(value="select * from seat where schedule_id=?1", nativeQuery=true)
    List<Seat> findSeatsByScheduleId(Long schedule_id);
    @Modifying(clearAutomatically = true)
    @Query(value = "update seat set is_selected = 1 where id = ?1",nativeQuery = true)
    void updateSelected(Long seat_id);
    @Modifying(clearAutomatically = true)
    @Query(value = "update seat set is_selected = 0 where id = ?1",nativeQuery = true)
    void updateunSelected(Long seat_id);
    @Modifying
    @Query(value = "update seat set is_available = 0 and is_selected = 1 where id = ?1",nativeQuery = true)
    void updateavailable(Long seat_id);
    @Query(value = "select * from seat where id = ?1",nativeQuery = true)
    Seat findSeatsById(Long seat_id);
}
