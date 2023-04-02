package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        model.addAttribute("times", timestampService.getLatestNItems(6));
        model.addAttribute("newTime", newTimestamp);
        model.addAttribute("categories", categoryService.getAllItems());
        return "index";
    }

    @GetMapping("/allActivity") 
    public String allActivity(Model model) {
        return this.findPaginated(1, "date","asc", model);
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

    @GetMapping("/allActivity/page/{pageNo}")
    public String findPaginated(@PathVariable ("pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<Timestamp> page = timestampService.findPaginated(pageNo, pageSize, sortField, sortDir);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTimestamps", page.getContent());
        model.addAttribute("hasContent", page.hasContent());
        return "allActivity";
    }
    


    
}
