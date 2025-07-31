package com.example.OrdersEditor.service;

import com.example.OrdersEditor.model.Customer;
import com.example.OrdersEditor.model.Employee;
import com.example.OrdersEditor.model.Order;
import com.example.OrdersEditor.repository.CustomerRepository;
import com.example.OrdersEditor.repository.EmployeeRepository;
import com.example.OrdersEditor.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}