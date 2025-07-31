package com.example.OrdersEditor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "POSITION")
    private String position;
    
    @Column(name = "SALARY")
    private Double salary;
    
    @Column(name = "HIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
}