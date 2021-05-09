package com.py.software.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Seat {
    @Id
    private Long id;
    private Long schedule_id;
    private int row;
    private int column;
    private int isAvailable;
    private int isSelected;
}
