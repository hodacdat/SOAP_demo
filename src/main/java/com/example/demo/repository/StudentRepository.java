package com.example.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.xml.school.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class StudentRepository {
    private static final Map<String, Student> students = new HashMap<>();

    @PostConstruct
    public void initData() {

        Student student = new Student();
        student.setName("Sajal");
        student.setStandard(5);
        student.setAddress("Pune");
        students.put(student.getName(), student);

        student = new Student();
        student.setName("Kajal");
        student.setStandard(5);
        student.setAddress("Chicago");
        students.put(student.getName(), student);

        student = new Student();
        student.setName("Lokesh");
        student.setStandard(6);
        student.setAddress("Delhi");
        students.put(student.getName(), student);

        student = new Student();
        student.setName("Sukesh");
        student.setStandard(7);
        student.setAddress("Noida");
        students.put(student.getName(), student);
    }

    public Student findStudent(String name) {
        Assert.notNull(name, "The Student's name must not be null");
        return students.get(name);
    }

    public boolean addNewStudent(Student student){
        Assert.notNull(student, "The student must not be null");
        if (students.containsKey(student.getName())){
            return false;
        }

        students.put(student.getName(), student);
        return true;
    }

    public boolean updateStudent(Student student){
        Assert.notNull(student, "The student must not be null");
        if (students.containsKey(student.getName())){
            students.replace(student.getName(), student);
            return true;
        }
        return false;
    }

    public Collection<Student> listAll(){
        return students.values();
    }
}