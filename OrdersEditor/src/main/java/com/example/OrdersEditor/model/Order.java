package com.example.OrdersEditor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @NotNull(message = "Customer ID is required")
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @NotNull(message = "Employee ID is required")
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @NotNull(message = "Order date is required")
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date orderDate;

    @Positive(message = "Total amount must be positive")
    @Column(name = "TOTAL_AMOUNT")
    private double totalAmount;
}