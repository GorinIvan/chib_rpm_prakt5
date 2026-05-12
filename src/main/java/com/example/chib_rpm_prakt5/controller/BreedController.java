package com.example.chib_rpm_prakt5.controller;

import com.example.chib_rpm_prakt5.model.Breed;
import com.example.chib_rpm_prakt5.repo.BreedRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/breeds")
public class BreedController {

    private final BreedRepository breedRepository;

    public BreedController(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("breeds", breedRepository.findAll());
        return "breeds/list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "breeds/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name,
                         @RequestParam(required = false) String description) {
        Breed breed = new Breed();
        breed.setName(name);
        breed.setDescription(description);
        breedRepository.save(breed);
        return "redirect:/breeds";
    }
}
