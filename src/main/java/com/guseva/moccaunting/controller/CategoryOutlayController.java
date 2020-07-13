package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.repo.CategoryOutlayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category_outlay")
public class CategoryOutlayController {
    private final CategoryOutlayRepo repo;

    @Autowired
    public CategoryOutlayController(CategoryOutlayRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<CategoryOutlay> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public CategoryOutlay getOne(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }
}
