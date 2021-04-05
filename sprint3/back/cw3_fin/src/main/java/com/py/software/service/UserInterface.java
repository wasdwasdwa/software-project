package com.py.software.service;

import com.py.software.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserInterface {
    User login(String user_name, String user_pwd);
    void updateUserInfo(User user);
    User findUserById(Long user_id);
    List<User> findUserLikeName(String name);
    void addUser(User user);
    void deleteUser(Long user_id);
    List<User> findAllUserInfos();
    Page<User> findAllUserBySplitPage(Integer page, Integer limit, String keyword);
}
