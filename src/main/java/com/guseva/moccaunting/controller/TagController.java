package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Tag;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import com.guseva.moccaunting.repo.TagRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {
    private final TagRepo tagRepo;
    private final OperationRepo operRepo;
    
    @Autowired
    public TagController(TagRepo tagRepo, OperationRepo operRepo) {
        this.tagRepo = tagRepo;
        this.operRepo = operRepo;
    }

    @GetMapping
    public List<Tag> list() {
        return tagRepo.findAll();
    }

    @GetMapping("{id}")
    public Tag getOne(@PathVariable Long id){
        return tagRepo.findById(id).orElseThrow(NotFound::new);
    }
    
    @PutMapping("{id}")
    public Tag update(
            @PathVariable Long id,
            @RequestBody Tag tag
    ) {
        Tag tagFromDB = tagRepo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(tag, tagFromDB, "id");
        return tagRepo.save(tagFromDB);
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagRepo.save(tag);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        var toDelete = tagRepo.findById(id).orElseThrow(NotFound::new);
        tagRepo.delete(toDelete);
    }

    // Получить все операции по тегу.
    @GetMapping("{id}/operations")
    public List<Operation> allOperations(@PathVariable Long id) {
        Tag tagFromDB = tagRepo.findById(id).orElseThrow(NotFound::new);
        return operRepo.findAllByTags(tagFromDB);
    }
}
