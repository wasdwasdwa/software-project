package com.py.software.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;
    private Long hall_id;
    private Long movie_id;
    private Date schedule_startTime;
    private Integer schedule_price;
    private Integer schedule_remain;
    private Integer schedule_state;
    @Transient
    private Hall scheduleHall;
    @Transient
    private Movie scheduleMovie;
    @Transient
    private List<Orderinfo> orderingList;
}
