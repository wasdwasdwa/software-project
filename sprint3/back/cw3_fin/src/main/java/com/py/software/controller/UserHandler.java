package com.py.software.controller;

import com.py.software.entity.User;
import com.py.software.service.ImpUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserHandler {
    @Autowired
    private ImpUserInterface impUserInterface;

    @GetMapping("/test")
    public User testService(){
        return impUserInterface.findUserById(1L);
    }
}
