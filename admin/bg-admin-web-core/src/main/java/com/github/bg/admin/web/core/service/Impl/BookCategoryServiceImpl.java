package com.github.bg.admin.web.core.service.Impl;

import com.github.bg.admin.web.core.constant.SystemStaticConst;
import com.github.bg.admin.web.core.dao.BookCategoryMapper;
import com.github.bg.admin.web.core.entity.BookCategory;
import com.github.bg.admin.web.core.entity.ReturnInfo;
import com.github.bg.admin.web.core.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    private BookCategoryMapper bookCategoryDao;

    @Override
    public ReturnInfo getAllCategory() {
        List<BookCategory> allCategory = bookCategoryDao.getAllCategory();
        return new ReturnInfo(SystemStaticConst.SUCCESS, "加载全部的书籍数据成功",allCategory);
    }

    @Override
    public List<BookCategory> get1category() {
        return bookCategoryDao.get1category();
    }

    @Override
    public List<BookCategory> get2categoryById(Integer id) {
        return bookCategoryDao.get2categoryById(id);
    }

    @Override
    public BookCategory getCategoryById(Integer id) {
        return bookCategoryDao.getCategoryById(id);
    }

    @Override
    public void save(BookCategory bookCategory) {

    }

    @Override
    public void update(BookCategory bookCategory) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
