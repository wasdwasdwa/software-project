package com.py.software.service;

import com.py.software.entity.User;
import com.py.software.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public boolean login(String user_name, String user_pwd) {
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        User user = userRepository.findUserByName(user_name);
        if(user==null){
            return false;
        }
        String pwd_DB = user.getUser_pwd();
        return encoder.matches(user_pwd.trim(),pwd_DB);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void Register(String user_name, String user_pwd) {
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        User user = userRepository.findUserByName(user_name);
        if(user!=null){
            return;
        }
        String pwd_db = "";
        User new_user = new User();
        new_user.setUser_name(user_name);
        pwd_db = encoder.encode(user_pwd.trim());
        new_user.setUser_pwd(pwd_db);
        User user1 = userRepository.save(new_user);
        userRepository.flush();
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public void updateUserInfo(String user_name,String question,String answer) {
        User user = userRepository.findUserByName(user_name);
        user.setUser_question(question);
        user.setUser_answer(answer);
        userRepository.save(user);
        userRepository.flush();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public User findUserById(Long user_id) {
        return userRepository.findById(user_id).get();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public boolean judgeQuestion(String username,String answer) {
        User user = userRepository.findUserByName(username);
        return user.getUser_answer().equals(answer);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public boolean resetPwd(String username, String pwd) {
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        User user = userRepository.findUserByName(username);
        User user1 = new User();
        user1.setUser_pwd(encoder.encode(pwd.trim()));
        user1.setUser_id(user.getUser_id());
        user1.setUser_name(user.getUser_name());
        user1.setUser_question(user.getUser_question());
        user1.setUser_answer(user.getUser_answer());
        userRepository.save(user1);
        userRepository.flush();
        return true;
    }


    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<User> findUserLikeName(String name) {
        return userRepository.findUsersLikeName(name);
    }

    @Override
    public String findQuestion(String user_name) {
        User user = userRepository.findUserByName(user_name);
        System.out.println(user.getUser_id());
        System.out.println(user.getUser_question());
        return user.getUser_question();
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
