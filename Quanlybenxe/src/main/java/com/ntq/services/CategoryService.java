/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services;

import java.util.List;
import com.ntq.pojo.Category;

/**
 *
 * @author ACER
 */
public interface CategoryService {

    List<Category> getCates();

    Category getCategoryById(int id);
    
     void addOrUpdate(Category c);
     
     void deleteCategory(int categoryID);
}
