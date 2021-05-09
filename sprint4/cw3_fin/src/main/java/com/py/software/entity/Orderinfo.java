package com.py.software.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Orderinfo {
    @Id
    private String order_id;
    private Long user_id;
    private Long schedule_id;
    private String order_position;
    private Integer order_state;
    private Integer order_price;
    private Date order_time;
    @Transient
    private User orderUser;
    @Transient
    private Schedule orderSchedule;
}
