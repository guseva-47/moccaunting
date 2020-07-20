package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Purse;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import com.guseva.moccaunting.repo.PurseRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purse")
public class PurseController {
    private final PurseRepo repo;
    private final OperationRepo operRepo;

    @Autowired
    public PurseController(PurseRepo repo, OperationRepo operRepo) {
        this.repo = repo;
        this.operRepo = operRepo;
    }

    @GetMapping
    public List<Purse> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Purse getOne(@PathVariable Long id) {
        return  repo.findById(id).orElseThrow(NotFound::new);
    }

    @GetMapping("{id}/operations")
    public List<Operation> allOperations(@PathVariable Long id) {
        Purse purseFromDB = repo.findById(id).orElseThrow(NotFound::new);
        return operRepo.findAllByPurse(purseFromDB);
    }

    @PutMapping("{id}")
    public Purse update(@PathVariable Long id, @RequestBody Purse purse) {
        Purse purseFromDB = repo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(purse, purseFromDB, "id");
        return repo.save(purseFromDB);
    }

    @PostMapping
    public Purse create(@RequestBody Purse purse) {
        purse.setIsActive(true);
        return repo.save(purse);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        var toDelete = repo.findById(id).orElseThrow(NotFound::new);
        repo.delete(toDelete);
    }
}
