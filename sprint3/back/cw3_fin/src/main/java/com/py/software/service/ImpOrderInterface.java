package com.py.software.service;

import com.py.software.entity.Orderinfo;
import com.py.software.entity.User;
import com.py.software.repository.OrderinfoRepository;
import com.py.software.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ImpOrderInterface implements OrderInfoInterface{

    @Autowired
    private OrderinfoRepository orderinfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Orderinfo findOrderById(Long order_id) {
        return orderinfoRepository.findById(order_id).get();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void addOrder(Orderinfo order) {
        orderinfoRepository.save(order);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void updateOrderToRefund(Long order_id) {
        orderinfoRepository.updateOrderToRefund(order_id);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public void updateOrderToRefunded(Long order_id) {
        orderinfoRepository.updateOrderToRefunded(order_id);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<Orderinfo> findOrderByUsername(Integer page, Integer limit, String username) {
        Pageable pageable = PageRequest.of(page-1,limit);
        User user = userRepository.findUserByName(username);
        return orderinfoRepository.findOrderByUserId(user.getUser_id(),pageable);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Orderinfo> findAllOrders() {
        return orderinfoRepository.findAll();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Orderinfo> findRefundByUsername(String username) {
        User user = userRepository.findUserByName(username);
        if(user == null){
            return null;
        }
        return orderinfoRepository.findRefundByUserId(user.getUser_id());
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Orderinfo> findRefundedByUsername(String username) {
        User user = userRepository.findUserByName(username);
        if(user == null){
            return null;
        }
        return orderinfoRepository.findRefundedByUserId(user.getUser_id());
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Page<Orderinfo> findOrderByState(Integer page, Integer limit, Integer State) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return orderinfoRepository.findOrderByState(State,pageable);
    }
}
