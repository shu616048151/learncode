package com.shu.vo;

import com.shu.domain.Class;
import com.shu.domain.Student;

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
