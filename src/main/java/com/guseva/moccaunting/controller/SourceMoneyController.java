package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.SourceMoney;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.SourceMoneyRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("source_money")
public class SourceMoneyController {
    private final SourceMoneyRepo repo;

    @Autowired
    public SourceMoneyController(SourceMoneyRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<SourceMoney> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public SourceMoney getOne(@PathVariable Long id){
        return repo.findById(id).orElseThrow(NotFound::new);
    }
    

    @PutMapping("{id}")
    public SourceMoney update(
            @PathVariable Long id,
            @RequestBody SourceMoney source
    ) {
        SourceMoney sourceFromDB = repo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(source, sourceFromDB, "id");
        return repo.save(sourceFromDB);
    }

    @PostMapping
    public SourceMoney create(@RequestBody SourceMoney source) {
        source.setIsActive(true);
        return repo.save(source);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        var toDelete = repo.findById(id).orElseThrow(NotFound::new);
        repo.delete(toDelete);
    }
}
