package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Timestamp;
import com.example.demo.service.TimestampService;
import com.example.demo.service.categoryService;


@Controller
public class AppController {

    private final TimestampService timestampService;
    private final categoryService categoryService;

    @Autowired
    public AppController(TimestampService timestampService, categoryService categoryService) {
        this.timestampService = timestampService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        Timestamp newTimestamp = new Timestamp();
        model.addAttribute("times", timestampService.getLatestNItems(6));
        model.addAttribute("newTime", newTimestamp);
        model.addAttribute("categories", categoryService.getAllItems());
        return "index";
    }

    @GetMapping("/allActivity") 
    public String allActivity(Model model) {
        model.addAttribute("items", timestampService.getAllItems());
        return "allActivity";
    }
            

    @PostMapping("/")
    public String saveTimestamp(@RequestBody Timestamp timestamp) {
        timestampService.addNewTimestamp(timestamp);
        System.out.println(timestamp);
        return "redirect:/test";
    }

    @PostMapping("/save")
    public String saveFormTimestamp(@ModelAttribute("newItem") Timestamp timestamp) {
        System.out.println(timestamp);
        timestampService.addNewTimestamp(timestamp);
        return "redirect:/";
    }
    


    
}
