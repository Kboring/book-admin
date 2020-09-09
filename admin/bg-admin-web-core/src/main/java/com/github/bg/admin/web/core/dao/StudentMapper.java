package com.github.bg.admin.web.core.dao;


import com.github.bg.admin.web.core.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public List<Student> findAll();
    public Student findById(Long id);
    public void save(Student student);
    public void update(Student student);
    public void deleteById(Long id);
}
