package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Category;
import com.example.demo.service.categoryService;

@Controller
@RequestMapping("/category")
public class categoryController {
    private final categoryService catService;

    @Autowired
    public categoryController(categoryService catService) {
        this.catService = catService;
    }

    @GetMapping
    public String home(Model model) {
        Category newCat = new Category();
        model.addAttribute("categories", catService.getAllItems());
        model.addAttribute("newCat", newCat);
        return "category";
    }

    @PostMapping
    public String addNewCategory(@ModelAttribute("newCat") Category category) {
        catService.addNewItem(category);
        return "redirect:/category";

    }

    @GetMapping("/delete/{id}")
    public String deleteCategoryById(@PathVariable("id") Long id) {
        catService.deleteItemById(id);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("edit", catService.getItemById(id));
        return "categoryEdit";
    }

    

    
}
