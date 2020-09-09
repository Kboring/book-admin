package com.github.bg.admin.web.core.service;

import com.github.bg.admin.web.core.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();
    public Student findById(Long id);
    public void save(Student student);
    public void update(Student student);
    public void deleteById(Long id);
}
