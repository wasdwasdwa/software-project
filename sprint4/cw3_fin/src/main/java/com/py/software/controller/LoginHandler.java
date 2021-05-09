package com.py.software.controller;

import com.py.software.service.ImpUserInterface;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginHandler {
    @Autowired
    private ImpUserInterface impUserInterface;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String login(@RequestBody HashMap<String,Map<String,String>> message){
        boolean flag = impUserInterface.login(message.get("data").get("username"),message.get("data").get("password"));
        if(!flag){
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String reset(@RequestBody HashMap<String,String> username){
        return impUserInterface.findQuestion(username.get("data"));
    }

    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public String changePwd(@RequestBody Map<String,Map<String,String>> map){
        System.out.println(map);
        boolean flag = impUserInterface.judgeQuestion(map.get("data").get("username"),map.get("data").get("answer"));
        if(!flag){
            return "wronganswer";
        }
        impUserInterface.resetPwd(map.get("data").get("username"),map.get("data").get("password"));
        return "success";
    }
}
