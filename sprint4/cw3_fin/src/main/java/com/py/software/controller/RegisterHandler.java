package com.py.software.controller;

import com.py.software.entity.User;
import com.py.software.service.ImpUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterHandler {
    @Autowired
    private ImpUserInterface impUserInterface;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public void Register(@RequestBody HashMap<String,Map<String,String>> message){
        impUserInterface.Register(message.get("data").get("username"),message.get("data").get("password"));
        impUserInterface.updateUserInfo(message.get("data").get("username"),message.get("data").get("selected"),message.get("data").get("answer"));
    }
}
