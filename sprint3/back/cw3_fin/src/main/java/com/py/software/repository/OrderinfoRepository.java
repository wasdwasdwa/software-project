package com.py.software.repository;

import com.py.software.entity.Orderinfo;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderinfoRepository extends JpaRepository<Orderinfo,Long> {

    @Modifying
    @Query(value = "update orderinfo set order_state = 0 where order_id = ?1",nativeQuery = true)
    void updateOrderToRefund(Long order_id);

    @Modifying
    @Query(value = "update orderinfo set order_state = 2 where order_id = ?1",nativeQuery = true)
    void updateOrderToRefunded(Long order_id);

    @Query("from Orderinfo k where k.user_id = ?1")
    Page<Orderinfo> findOrderByUserId(Long user_id, Pageable pageable);

    @Query(value = "select * from orderinfo where user_id = ?1 and order_state = 0",nativeQuery = true)
    List<Orderinfo> findRefundByUserId(Long user_id);

    @Query(value = "select * from orderinfo where user_id = ?1 and order_state = 2",nativeQuery = true)
    List<Orderinfo> findRefundedByUserId(Long user_id);

    @Query("from Orderinfo k where k.order_state = ?1")
    Page<Orderinfo> findOrderByState(int order_state, Pageable pageable);
}
