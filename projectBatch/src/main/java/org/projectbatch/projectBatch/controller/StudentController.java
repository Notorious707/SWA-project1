package org.projectbatch.projectBatch.controller;

import org.projectbatch.projectBatch.model.Student;
import org.projectbatch.projectBatch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

//    @GetMapping("/trigger")


    @GetMapping("/getall")
    public List<Student> getAll(){
        return studentService.getAll();
    }
}
