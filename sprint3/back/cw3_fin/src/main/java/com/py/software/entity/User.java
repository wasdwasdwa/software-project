package com.py.software.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String user_name;
    private String user_pwd;
    private String user_answer;
    private String user_question;
}
