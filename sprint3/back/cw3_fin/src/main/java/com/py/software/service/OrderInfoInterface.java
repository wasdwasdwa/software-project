package com.py.software.service;

import com.py.software.entity.Orderinfo;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderInfoInterface {
    Orderinfo findOrderById(Long order_id);
    void addOrder(Orderinfo order);
    void updateOrderToRefund(Long order_id);
    void updateOrderToRefunded(Long order_id);
    Page<Orderinfo> findOrderByUsername(Integer page,Integer limit,String username);
    List<Orderinfo> findAllOrders();
    List<Orderinfo> findRefundByUsername(String username);
    List<Orderinfo> findRefundedByUsername(String username);
    Page<Orderinfo> findOrderByState(Integer page,Integer limit,Integer State);
}
