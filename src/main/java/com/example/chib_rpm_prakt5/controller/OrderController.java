package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Order;
import com.example.chib_rpm_prakt5.model.User;
import com.example.chib_rpm_prakt5.repo.OrderRepository;
import com.example.chib_rpm_prakt5.repo.UserRepository;
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
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "orders/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam Integer userId,
                         @RequestParam(required = false) String orderStatus,
                         @RequestParam(required = false) BigDecimal totalAmount,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime orderDate,
                         @RequestParam(required = false) String paymentMethod,
                         @RequestParam(required = false) String paymentStatus) {
        User user = userRepository.findById(userId).orElse(null);

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(orderStatus);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(orderDate);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(paymentStatus);

        orderRepository.save(order);
        return "redirect:/orders";
    }
}
