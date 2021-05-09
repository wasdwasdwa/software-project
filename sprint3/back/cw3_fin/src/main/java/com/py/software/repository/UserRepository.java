package com.py.software.repository;

import com.py.software.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user  where user_name=?1",nativeQuery = true)
    User findUserByName(String name);
    @Query(value = "select u from user where user_name = ?1 and user_pwd = ?2",nativeQuery = true)
    User Login(String username,String pwd);
    @Query(value = "select * from user where user_name like concat('%',?1,'%') ",nativeQuery = true)
    List<User> findUsersLikeName(String name);
}
