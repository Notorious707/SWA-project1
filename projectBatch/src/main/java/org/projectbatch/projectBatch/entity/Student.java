package org.projectbatch.projectBatch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first;
    private String last;
    private Double gpa;
    private LocalDate dob;

    public Student(String first, String last, Double gpa, LocalDate dob) {
        this.first = first;
        this.last = last;
        this.gpa = gpa;
        this.dob = dob;
    }
}
