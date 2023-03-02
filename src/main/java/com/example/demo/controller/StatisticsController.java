package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.TimestampService;
import com.example.demo.service.categoryService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    private final TimestampService timestampService;
    private final categoryService categoryService;

    @Autowired
    public StatisticsController(TimestampService timestampService, categoryService categoryService) {
        this.timestampService = timestampService;
        this.categoryService = categoryService;
    }

    @GetMapping("/specific")
    public String home(@RequestParam(required = false, name = "dayDate") LocalDate dayDate,@RequestParam(required = false, name = "year") Integer year, @RequestParam(required = false, name = "week") Integer week,  Model model) {

        if (dayDate == null && week == null && year == null){
            return "redirect:/";
        }

        if (dayDate == null) {
            model.addAttribute("times", timestampService.getHoursByDay(LocalDate.now()));
        } else {
            model.addAttribute("times", timestampService.getHoursByDay(dayDate));
        }
        if (week == null) {
            model.addAttribute("weekTime", timestampService.getHoursByWeek(year,timestampService.getDistinctWeeksByYear(year).get(0)));
        } else {
            model.addAttribute("weekTime", timestampService.getHoursByWeek(year,week));
        }
        model.addAttribute("weeks", timestampService.getDistinctWeeksByYear(year));
        model.addAttribute("years", timestampService.getDistinctYears());

        model.addAttribute("prevYear", year);
        model.addAttribute("prevWeek", week);
        return "statistics";
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("times", timestampService.getHoursByDay(LocalDate.now()));
        List<Integer> distinctYears = timestampService.getDistinctYears();
        List<Integer> distinctWeeks;
        if (distinctYears.isEmpty()) {
            model.addAttribute("weekTime", List.of());
            model.addAttribute("weeks", List.of()); 
        } else {
            distinctWeeks = timestampService.getDistinctWeeksByYear(distinctYears.get(0));
            model.addAttribute("weekTime", timestampService.getHoursByWeek(distinctYears.get(0), distinctWeeks.get(0)));
            model.addAttribute("weeks", distinctWeeks); 

        }
        
        model.addAttribute("years", distinctYears);
        return "statistics";
    }
    
}
