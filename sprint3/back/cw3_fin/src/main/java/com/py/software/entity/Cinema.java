package com.py.software.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinema_id;
    private String cinema_name;
    private String cinema_address;
    @Transient
    private List<Hall> hallList;
}
