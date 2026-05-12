package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Breed;
import com.example.chib_rpm_prakt5.model.Cat;
import com.example.chib_rpm_prakt5.repo.BreedRepository;
import com.example.chib_rpm_prakt5.repo.CatRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/cats")
public class CatController {

    private final CatRepository catRepository;
    private final BreedRepository breedRepository;

    public CatController(CatRepository catRepository, BreedRepository breedRepository) {
        this.catRepository = catRepository;
        this.breedRepository = breedRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cats", catRepository.findAll());
        return "cats/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("breeds", breedRepository.findAll());
        return "cats/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
                         @RequestParam(required = false) String color,
                         @RequestParam(required = false) String pedigree,
                         @RequestParam(required = false) BigDecimal price,
                         @RequestParam(required = false) String status,
                         @RequestParam Integer breedId) {
        Breed breed = breedRepository.findById(breedId).orElse(null);
        Cat cat = new Cat();
        cat.setName(name);
        cat.setBirthDate(birthDate);
        cat.setColor(color);
        cat.setPedigree(pedigree);
        cat.setPrice(price);
        cat.setStatus(status);
        cat.setBreed(breed);
        catRepository.save(cat);
        return "redirect:/cats";
    }
}
