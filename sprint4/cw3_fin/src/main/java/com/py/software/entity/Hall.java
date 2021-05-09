package com.py.software.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hall_id;
    private String hall_name;
    private Integer hall_capacity;
    private Long cinema_id;
    @Transient
    private List<Schedule> scheduleList;
    @Transient
    private Cinema hallCinema;
    @Transient
    private  List<Orderinfo> OrderInfoList;
}
