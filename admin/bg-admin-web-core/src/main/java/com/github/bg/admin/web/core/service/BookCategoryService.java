package com.github.bg.admin.web.core.service;

import com.github.bg.admin.web.core.entity.BookCategory;
import com.github.bg.admin.web.core.entity.ReturnInfo;

import java.util.List;

public interface BookCategoryService {
    public ReturnInfo getAllCategory();               //查询所有分类--项目用不到，用于测试
    public java.util.List<BookCategory> get1category();               //查询一级分类，即parentId=0的
    public List<BookCategory> get2categoryById(Integer id); //根据父类ID，查询二级分类ID
    public BookCategory getCategoryById(Integer id);         //根据分类ID，返回该分类的信息
    public void save(BookCategory bookCategory);            //插入新分类
    public void update(BookCategory bookCategory);          //更新
    public void deleteById(Integer id);                     //根据分类ID，删除该分类
}
