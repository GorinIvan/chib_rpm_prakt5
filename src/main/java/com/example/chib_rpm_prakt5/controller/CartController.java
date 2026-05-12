package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Cart;
import com.example.chib_rpm_prakt5.repo.CartRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/carts")
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("carts", cartRepository.findAll());
        return "carts/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "carts/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAt,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedAt) {
        Cart cart = new Cart();
        cart.setCreatedAt(createdAt);
        cart.setUpdatedAt(updatedAt);
        cartRepository.save(cart);
        return "redirect:/carts";
    }
}
