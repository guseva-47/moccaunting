package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.CategoryOutlayRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        return repo.findById(id).orElseThrow(NotFound::new);
    }

//    @GetMapping("{id}")
//    public CategoryOutlay getOne(@PathVariable("id") CategoryOutlay categoryOutlay){
//        return categoryOutlay;
//    }

    @PutMapping("{id}")
    public CategoryOutlay update(
            @PathVariable Long id,
            @RequestBody CategoryOutlay category
    ) {
        CategoryOutlay categoryFromDB = repo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(category, categoryFromDB, "id");
        return repo.save(categoryFromDB);
    }

    @PostMapping
    public CategoryOutlay create(@RequestBody CategoryOutlay category) {
        var a = repo.save(category);
        return a;
    }
}
