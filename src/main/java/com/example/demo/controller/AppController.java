package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Page<Timestamp> page = timestampService.getLatestNItems(5);

        model.addAttribute("times", page.getContent());
        model.addAttribute("hasNextPage", page.hasNext());
        model.addAttribute("hasContent", page.hasContent());
        model.addAttribute("newTime", newTimestamp);
        model.addAttribute("categories", categoryService.getAllItems());
        return "index";
    }

    @PostMapping("/save")
    public String saveFormTimestamp(@ModelAttribute Timestamp timestamp) {
        timestampService.addNewTimestamp(timestamp);
        return "redirect:";
    }

}
