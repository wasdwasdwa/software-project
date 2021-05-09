package com.py.software.controller;

import com.py.software.entity.Hall;
import com.py.software.entity.Schedule;
import com.py.software.service.ImpHallInterface;
import com.py.software.service.ImpScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hall")
public class HallHandler {
    @Autowired
    private ImpScheduleInterface impScheduleInterface;

    @Autowired
    private ImpHallInterface impHallInterface;
    @GetMapping("/schedule_id")
    public Map<String,Object> getHall(@RequestParam(value = "tid")Long tid){
        Map<String,Object> result = new HashMap<>();
        Schedule schedule = impScheduleInterface.findScheduleById(tid);
        schedule.setNewTime(schedule.getSchedule_start_time().substring(11));
        Hall hall = impHallInterface.findHallById(schedule.getHall_id());
        result.put("hall",hall);
        result.put("schedule",schedule);
        return result;
    }

}
