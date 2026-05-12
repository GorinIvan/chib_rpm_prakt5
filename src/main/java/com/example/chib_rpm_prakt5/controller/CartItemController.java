package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Cart;
import com.example.chib_rpm_prakt5.model.CartItem;
import com.example.chib_rpm_prakt5.model.Cat;
import com.example.chib_rpm_prakt5.repo.CartItemRepository;
import com.example.chib_rpm_prakt5.repo.CartRepository;
import com.example.chib_rpm_prakt5.repo.CatRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CatRepository catRepository;

    public CartItemController(CartItemRepository cartItemRepository,
                              CartRepository cartRepository,
                              CatRepository catRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.catRepository = catRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cartItems", cartItemRepository.findAll());
        return "cart-items/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("carts", cartRepository.findAll());
        model.addAttribute("cats", catRepository.findAll());
        return "cart-items/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam Integer cartId,
                         @RequestParam Integer catId,
                         @RequestParam Integer quantity,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime addedAt) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Cat cat = catRepository.findById(catId).orElse(null);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCat(cat);
        cartItem.setQuantity(quantity);
        cartItem.setAddedAt(addedAt);
        cartItemRepository.save(cartItem);
        return "redirect:/cart-items";
    }
}
