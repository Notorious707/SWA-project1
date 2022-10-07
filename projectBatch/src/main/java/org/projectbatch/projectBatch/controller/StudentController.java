package org.projectbatch.projectBatch.controller;

import org.projectbatch.projectBatch.entity.Student;
import org.projectbatch.projectBatch.service.StudentService;
import org.springframework.batch.core.Job;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/trigger")
    public void trigger() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job, jobParameters);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/getall")
    public List<Student> getAll(){
        return studentService.getAll();
    }
}
