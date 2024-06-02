/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.controllers;

import com.ntq.pojo.Category;
import com.ntq.services.CategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryServices;

    @GetMapping("/categories")
    public String createView(Model model) {

        model.addAttribute("category", new Category());
        return "categories";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute(value = "category") @Valid Category c,
            BindingResult rs, Model model) {
        if (!rs.hasErrors()) {
            try {
                this.categoryServices.addOrUpdate(c);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        model.addAttribute("category", c);
        
        return "categories";
    }

    @GetMapping("/categories/{categoryID}")
    public String updateView(Model model, @PathVariable(value = "categoryID") int categoryID) {
        model.addAttribute("category", this.categoryServices.getCategoryById(categoryID));

        return "categories";
    }
}
