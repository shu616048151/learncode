package com.springboot.vo;

import com.springboot.domain.Class;
import com.springboot.domain.Student;

import java.util.List;

public class ClassVo extends Class {
    private List<Student> student;

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }


}
