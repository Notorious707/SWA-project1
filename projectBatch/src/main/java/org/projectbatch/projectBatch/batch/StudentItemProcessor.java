package org.projectbatch.projectBatch.batch;

import org.projectbatch.projectBatch.entity.Student;
import org.projectbatch.projectBatch.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.Year;

public class StudentItemProcessor implements ItemProcessor<StudentDto,Student> {
    private static final Logger log = LoggerFactory.getLogger(StudentItemProcessor.class);

    @Override
    public Student process(StudentDto studentDto) throws Exception {
        log.info("processing student's age here...");

        int year = Year.now().getValue();
        LocalDate date = LocalDate.of(year-studentDto.getAge(), 1, 1);

        Student student = new Student();
        student.setFirst(studentDto.getFirstName());
        student.setLast(studentDto.getLastName());
        student.setGpa(studentDto.getGpa());
        student.setDob(date);
        System.out.println("PRINT: " + student.toString());
        return student;
    }
}
