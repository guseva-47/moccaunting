package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Tag;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.CategoryOutlayRepo;
import com.guseva.moccaunting.repo.OperationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category_outlay")
public class CategoryOutlayController {
    private final CategoryOutlayRepo repo;
    private final OperationRepo operRepo;

    @Autowired
    public CategoryOutlayController(CategoryOutlayRepo repo, OperationRepo operRepo) {
        this.repo = repo;
        this.operRepo = operRepo;
    }

    @GetMapping
    public List<CategoryOutlay> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public CategoryOutlay getOne(@PathVariable Long id){
        return repo.findById(id).orElseThrow(NotFound::new);
    }

    @GetMapping("{id}/operations")
    public List<Operation> allOperations(@PathVariable Long id) {
        CategoryOutlay categoryFromDB = repo.findById(id).orElseThrow(NotFound::new);
        return operRepo.findAllByCategoryOutlay(categoryFromDB);
    }

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
        category.setIsActive(true);
        return repo.save(category);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        var toDelete = repo.findById(id).orElseThrow(NotFound::new);
        repo.delete(toDelete);
    }
}
