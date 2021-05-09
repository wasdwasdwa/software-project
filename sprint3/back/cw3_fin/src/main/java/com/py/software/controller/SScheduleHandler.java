package com.py.software.controller;

import com.py.software.entity.Schedule;
import com.py.software.service.ImpScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/schedule")
public class SScheduleHandler {
    @Autowired
    private ImpScheduleInterface impScheduleInterface;

    @GetMapping("/schedule_id")
    public Schedule getScheule(@RequestParam(value = "sid")Long schedule_id) throws ParseException {
        Schedule schedule = impScheduleInterface.findScheduleById(schedule_id);
        SimpleDateFormat py_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date newtime = py_1.parse(schedule.getSchedule_start_time());
        schedule.setNewTime(schedule.getSchedule_start_time().substring(11));
        return schedule;
    }
}
