package com.ntq.controllers;

import com.ntq.pojo.Category;
import com.ntq.services.CategoryService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ApiCategoryController {

    @Autowired
    private CategoryService cateService;

    @GetMapping("/categories")
    @CrossOrigin
    public ResponseEntity<List<Category>> list() {
        return new ResponseEntity<>(this.cateService.getCates(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/categories/{categoryID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> retrieve(@PathVariable(value = "categoryID") int categoryID) {
        return new ResponseEntity<>(this.cateService.getCategoryById(categoryID), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "categoryID") int categoryID) {
//        this.cateService.deleteCategory(categoryID);
        Category category = this.cateService.getCategoryById(categoryID);
        if (category != null) {
            this.cateService.deleteCategory(categoryID);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
    
    @PostMapping(path = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createCategory (@RequestParam Map<String, String> params) {
        Category category = new Category();
        category.setName(params.get("name"));
        category.setDescription(params.get("description"));
        category.setCreatedAt(new Date());
        
        
        this.cateService.addOrUpdate(category);
    }
}
