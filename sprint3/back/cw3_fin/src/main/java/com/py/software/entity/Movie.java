package com.py.software.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;
    private String movie_cn_name;
    private String movie_fg_name;
    private String movie_actor;
    private String movie_director;
    private String movie_detail;
    private String movie_duration;
    private String movie_type;
    private Float movie_score;
    private Float movie_boxOffice;
    private Long movie_commentCount;
    private Date movie_releaseDate;
    private String movie_country;
    private String movie_picture;
    private Integer movie_state;
    @Transient
    private List<Comment> commentList;
}
