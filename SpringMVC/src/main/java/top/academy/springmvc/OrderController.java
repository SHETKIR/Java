package top.academy.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private List<Order> orders = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public OrderController() {
        Order order1 = new Order();
        order1.setId(idGenerator.getAndIncrement());
        order1.setCustomerName("Иван Иванов");
        order1.setCustomerEmail("ivan@example.com");
        order1.setItemName("Пицца Маргарита");
        order1.setQuantity(2);
        order1.setPrice(new BigDecimal("15.99"));
        order1.setStatus("NEW");
        order1.setOrderDate(LocalDateTime.now().minusDays(1));
        orders.add(order1);

        Order order2 = new Order();
        order2.setId(idGenerator.getAndIncrement());
        order2.setCustomerName("Мария Петрова");
        order2.setCustomerEmail("maria@example.com");
        order2.setItemName("Спагетти Карбонара");
        order2.setQuantity(1);
        order2.setPrice(new BigDecimal("12.50"));
        order2.setStatus("IN_PROGRESS");
        order2.setOrderDate(LocalDateTime.now().minusHours(2));
        orders.add(order2);
    }

    @GetMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    @GetMapping("/add")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("formAction", "/orders/add");
        return "orders/form";
    }

    @PostMapping("/add")
    public String saveNewOrder(@ModelAttribute Order order) {
        order.setId(idGenerator.getAndIncrement());
        order.setOrderDate(LocalDateTime.now());
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("NEW");
        }
        orders.add(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        Order order = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if (order == null) {
            return "redirect:/orders/list";
        }
        
        model.addAttribute("order", order);
        model.addAttribute("formAction", "/orders/edit/" + id);
        return "orders/form";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        Order existingOrder = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if (existingOrder != null) {
            existingOrder.setCustomerName(order.getCustomerName());
            existingOrder.setCustomerEmail(order.getCustomerEmail());
            existingOrder.setItemName(order.getItemName());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setPrice(order.getPrice());
            existingOrder.setStatus(order.getStatus());
        }
        
        return "redirect:/orders/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orders.removeIf(order -> order.getId().equals(id));
        return "redirect:/orders/list";
    }
}