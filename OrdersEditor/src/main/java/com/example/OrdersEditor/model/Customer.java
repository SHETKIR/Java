package com.example.OrdersEditor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMERS")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "EMAIL")
    private String email;
}