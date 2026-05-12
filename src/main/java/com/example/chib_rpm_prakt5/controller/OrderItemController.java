package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Cat;
import com.example.chib_rpm_prakt5.model.Order;
import com.example.chib_rpm_prakt5.model.OrderItem;
import com.example.chib_rpm_prakt5.repo.CatRepository;
import com.example.chib_rpm_prakt5.repo.OrderItemRepository;
import com.example.chib_rpm_prakt5.repo.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CatRepository catRepository;

    public OrderItemController(OrderItemRepository orderItemRepository,
                               OrderRepository orderRepository,
                               CatRepository catRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.catRepository = catRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orderItems", orderItemRepository.findAll());
        return "order-items/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("cats", catRepository.findAll());
        return "order-items/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam Integer orderId,
                         @RequestParam Integer catId,
                         @RequestParam Integer quantity,
                         @RequestParam(required = false) BigDecimal priceAtPurchase) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Cat cat = catRepository.findById(catId).orElse(null);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setCat(cat);
        orderItem.setQuantity(quantity);
        orderItem.setPriceAtPurchase(priceAtPurchase);

        orderItemRepository.save(orderItem);
        return "redirect:/order-items";
    }
}
