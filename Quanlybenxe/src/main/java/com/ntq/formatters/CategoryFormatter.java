/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.formatters;

import com.ntq.pojo.Category;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class CategoryFormatter implements Formatter<Category> {

    @Override
    public String print(Category cate, Locale locale) {
        return String.valueOf(cate.getCategoryID());
    }

    @Override
    public Category parse(String categoryID, Locale locale) throws ParseException {
        Category c = new Category();
        c.setCategoryID(Integer.parseInt(categoryID));

        return c;
    }

}
