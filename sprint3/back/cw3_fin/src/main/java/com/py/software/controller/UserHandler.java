package com.py.software.controller;

import com.py.software.entity.User;
import com.py.software.repository.UserRepository;
import com.py.software.service.ImpCinemaInterface;
import com.py.software.service.ImpUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserHandler {
    @Autowired
    private ImpUserInterface impUserInterface;
    @Autowired
    private ImpCinemaInterface impCinemaInterface;

    @GetMapping("/test/{temp}")
    public List<User> testService(@PathVariable("temp") String temp){
        return impUserInterface.findUserLikeName(temp);
    }
}
