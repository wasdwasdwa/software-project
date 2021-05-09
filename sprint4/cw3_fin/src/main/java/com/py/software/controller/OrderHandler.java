package com.py.software.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.py.software.entity.Orderinfo;
import com.py.software.entity.Seat;
import com.py.software.service.ImpOrderInterface;
import com.py.software.service.ImpScheduleInterface;
import com.py.software.service.ImpSeatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderHandler {
    @Autowired
    private ImpOrderInterface impOrderInterface;

    @Autowired
    private ImpScheduleInterface impScheduleInterface;

    @Autowired
    private ImpSeatInterface impSeatInterface;

    @GetMapping("/myorder")
    public List<Orderinfo> getMyOrder(@RequestParam(value="data")String username){
        System.out.println(username);
        username = "wxj";
        return impOrderInterface.findOrderByName(username);
    }

    @GetMapping("/updateorder")
    public Map<String,Object> updateOrder(@RequestParam(value = "tid")Long schedule_id, @RequestParam(value = "mid")Long movie_id, @RequestParam(value = "user")String username,
                            @RequestParam(value = "array[]") int[] array){
        System.out.println(username);
        Map<String,Object> map = new HashMap<>();
        for(int i=0;i<array.length;i++){
            Seat temp = impSeatInterface.findById((long) array[i]);
            if(temp.getIsAvailable()==0){
                map.put("Flag",false);
                return map;
            }
        }
        Long seat_id = 10000L;
        Orderinfo orderinfo =  new Orderinfo();
        orderinfo.setOrder_price(40);
        orderinfo.setOrder_state(1);
        orderinfo.setOrder_time(new Date());
        orderinfo.setUser_id(1L);
        orderinfo.setSchedule_id(impScheduleInterface.findScheduleById(schedule_id).getSchedule_id());
        String py="";
        List<Seat> seatList = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            Seat seat = impSeatInterface.findById((long)array[i]);
            impSeatInterface.seatUnavailable(seat);
            py = seat.getRow()+"行"+seat.getColumn()+"列";
            seatList.add(seat);
        }
        orderinfo.setOrder_position(py);
        Date today = new Date();
        SimpleDateFormat py_1 = new SimpleDateFormat("yyyyMMdd");
        String today1 = py_1.format(today);
        Long temp = Long.valueOf(today1);
        temp*=100000000;
        temp+=movie_id*10000;
        Seat seat = impSeatInterface.findById((long)array[0]);
        impSeatInterface.seatUnavailable(seat);
        temp+=seat.getRow()*100+seat.getColumn();
        orderinfo.setOrder_id(temp.toString());
        impOrderInterface.addOrder(orderinfo);
        map.put("Flag",true);
        map.put("seats",seatList);
        System.out.println(map);
        return map;
    }
}
