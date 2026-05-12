package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Order;
import com.example.chib_rpm_prakt5.repo.OrderRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "orders/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam(required = false) String orderStatus,
                         @RequestParam(required = false) BigDecimal totalAmount,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime orderDate,
                         @RequestParam(required = false) String paymentMethod,
                         @RequestParam(required = false) String paymentStatus) {
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(orderDate);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(paymentStatus);

        orderRepository.save(order);
        return "redirect:/orders";
    }
}
