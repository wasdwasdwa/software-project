package com.py.software.service;

import com.py.software.entity.User;
import com.py.software.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ImpUserInterface implements UserInterface{
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public User login(String user_name, String user_pwd) {
        return userRepository.Login(user_name,user_pwd);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void updateUserInfo(User user) {
        userRepository.save(user);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public User findUserById(Long user_id) {
        return userRepository.findById(user_id).get();
    }


    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<User> findUserLikeName(String name) {
        return userRepository.findUsersLikeName(name);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<User> findAllUserInfos() {
        return userRepository.findAll();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<User> findAllUserBySplitPage(Integer page, Integer limit, String keyword) {
        Pageable pageable = PageRequest.of(page-1,limit);
        List<User> userList = userRepository.findUsersLikeName(keyword);
        Page<User> userPage = new PageImpl(userList,pageable,userList.size());
        return userPage;
    }
}
