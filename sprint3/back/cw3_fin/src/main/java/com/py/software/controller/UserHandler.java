package com.py.software.controller;

import com.py.software.entity.User;
import com.py.software.repository.UserRepository;
import com.py.software.service.ImpCinemaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImpCinemaInterface impCinemaInterface;

    @GetMapping("/test/{id}")
    public User testService(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }
}
