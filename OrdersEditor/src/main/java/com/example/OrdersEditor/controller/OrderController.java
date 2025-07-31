package com.example.OrdersEditor.controller;

import com.example.OrdersEditor.model.Order;
import com.example.OrdersEditor.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders/list";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", orderService.getAllCustomers());
        model.addAttribute("employees", orderService.getAllEmployees());
        return "orders/form";
    }

    @PostMapping("/save")
    public String saveOrder(@Valid @ModelAttribute Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", orderService.getAllCustomers());
            model.addAttribute("employees", orderService.getAllEmployees());
            return "orders/form";
        }
        if (order.getOrderDate() == null) {
            order.setOrderDate(new Date());
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String editOrder(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            model.addAttribute("order", order);
            model.addAttribute("customers", orderService.getAllCustomers());
            model.addAttribute("employees", orderService.getAllEmployees());
            return "orders/form";
        } else {
            return "redirect:/orders";
        }
    }

    @PostMapping("/update")
    public String updateOrder(@Valid @ModelAttribute Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", orderService.getAllCustomers());
            model.addAttribute("employees", orderService.getAllEmployees());
            return "orders/form";
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}