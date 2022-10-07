package org.projectbatch.projectBatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private Double gpa;
    private int age;

}
