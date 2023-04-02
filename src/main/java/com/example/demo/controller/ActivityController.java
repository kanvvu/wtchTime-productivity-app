package com.example.demo.controller;

import com.example.demo.model.Timestamp;
import com.example.demo.service.TimestampService;
import com.example.demo.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    private final TimestampService timestampService;
    private final com.example.demo.service.categoryService categoryService;

    @Autowired
    public ActivityController(TimestampService timestampService, categoryService categoryService) {
        this.timestampService = timestampService;
        this.categoryService = categoryService;
    }
    @GetMapping()
    public String allActivity(Model model) {
        return this.findPaginated(1, "date","asc", model);
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
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
        return "activity";
    }

    @GetMapping("/edit/{id}")
    public String editTimestampById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("timestamp", timestampService.getItemById(id));
        model.addAttribute("categories", categoryService.getAllItems());
        model.addAttribute("test",timestampService.getLatestNItems(1));
        return "timestampEdit";
    }

    @PostMapping("/save")
    public String saveFormTimestamp(@ModelAttribute Timestamp timestamp) {
        timestampService.addNewTimestamp(timestamp);
        return "redirect:/activity";
    }
}
