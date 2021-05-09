package com.py.software.controller;

import com.py.software.entity.Schedule;
import com.py.software.service.ImpScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/movieCinemaTime")
public class ScheduleHandler {

    @Autowired
    private ImpScheduleInterface impScheduleInterface;

    @GetMapping("")
    public Map<String, Object> findSchedule(@RequestParam(value = "cid") Long cinema_id,@RequestParam(value = "mid") Long movie_id) throws ParseException {
        List<Schedule> scheduleList = impScheduleInterface.findScheduleByCinemaAndMovie(cinema_id,movie_id);
        Map<String,Object> result = new HashMap<>();
        List<Schedule> one = new ArrayList<>();
        List<Schedule> two = new ArrayList<>();
        List<Schedule> three = new ArrayList<>();
        List<Schedule> four = new ArrayList<>();
        SimpleDateFormat py_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String cur_y = "2019-07-21 ";
        SimpleDateFormat py_2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat py_3 = new SimpleDateFormat("yyyy-MM-dd");
        Date cur = new Date();
        String cur_s = py_2.format(cur);
        Date make = py_1.parse(cur_y+cur_s);
        for(Schedule schedule:scheduleList){
            Date newtime = py_1.parse(schedule.getSchedule_start_time());
            schedule.setNewTime(schedule.getSchedule_start_time().substring(11));
            if(newtime.after(make)){
                int days = (int) ((py_3.parse(py_3.format(newtime)).getTime() - py_3.parse(cur_y).getTime()) / (1000*3600*24));
                switch (days){
                    case 0:
                        one.add(schedule);
                        break;
                    case 1:
                        two.add(schedule);
                        break;
                    case 2:
                        three.add(schedule);
                        break;
                    case 3:
                        four.add(schedule);
                        break;
                    default:
                        break;
                }
            }
        }
        result.put("today",one);
        result.put("today+1",two);
        result.put("today+2",three);
        result.put("today+3",four);
        return result;
    }
}
