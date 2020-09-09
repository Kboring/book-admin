package com.github.bg.admin.web.core.controller;

import com.github.bg.admin.web.core.constant.SystemStaticConst;
import com.github.bg.admin.web.core.entity.BookCategory;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.service.BookCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//直接返回json数据
@RestController
@RequestMapping("book")
public class BookCategoryController {
    //    自动注入BookCategoryRepository对象
    @Autowired
    private BookCategoryService bookCategoryService;


    @RequestMapping("/getAllCategory")
    public ReturnInfo getAllCategory() {
        return bookCategoryService.getAllCategory();
    }

    @GetMapping("/get1category")
    public List<BookCategory> get1category() {
        return bookCategoryService.get1category();
    }

    @GetMapping("/get2categoryById/{id}")
    public List<BookCategory> get2categoryById(@PathVariable("id") Integer  id) {
        return bookCategoryService.get2categoryById(id);
    }

    //    PathVariable将请求id赋值给形参
    @GetMapping("/getCategoryById/{id}")
    public BookCategory getCategoryById(@PathVariable("id") Integer id){
        return bookCategoryService.getCategoryById(id);
    }

    //    RequestBody将请求的json对象转换成java对象
    //    ResponseBody将java对象转换成json对象返回客户端
    @PostMapping("/saveCategory")
    public void save (@RequestBody BookCategory bookCategory){
        bookCategoryService.save(bookCategory);
    }

    @PutMapping("/updateCategory")
    public void update(@RequestBody BookCategory bookCategory) {
        bookCategoryService.update(bookCategory);
    }

    @DeleteMapping("/deleteCategoryById/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        bookCategoryService.deleteById(id);
    }
}
