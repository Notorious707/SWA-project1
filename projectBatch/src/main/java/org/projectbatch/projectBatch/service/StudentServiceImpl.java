package org.projectbatch.projectBatch.service;

import org.projectbatch.projectBatch.entity.Student;
import org.projectbatch.projectBatch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
