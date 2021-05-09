package com.py.software.service;

import com.py.software.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserInterface {
    boolean login(String user_name, String user_pwd);
    void Register(String user_name,String user_pwd);
    void updateUserInfo(String user_name,String question,String answer);
    User findUserById(Long user_id);
    boolean judgeQuestion(String username,String answer);
    boolean resetPwd(String username,String pwd);
    List<User> findUserLikeName(String name);
    String findQuestion(String user_name);
    void addUser(User user);
    void deleteUser(Long user_id);
    List<User> findAllUserInfos();
    Page<User> findAllUserBySplitPage(Integer page, Integer limit, String keyword);
}
