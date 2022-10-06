package org.projectbatch.projectBatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private Double gpa;
    private Date dob;

    public Student(String firstName, String lastName, Double gpa, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.dob = dob;
    }
}
