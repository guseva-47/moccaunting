package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Tag;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.TagRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {
    private final TagRepo repo;
    
    @Autowired
    public TagController(TagRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Tag> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Tag getOne(@PathVariable Long id){
        return repo.findById(id).orElseThrow(NotFound::new);
    }
    
    @PutMapping("{id}")
    public Tag update(
            @PathVariable Long id,
            @RequestBody Tag tag
    ) {
        Tag tagFromDB = repo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(tag, tagFromDB, "id");
        return repo.save(tagFromDB);
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return repo.save(tag);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        var toDelete = repo.findById(id).orElseThrow(NotFound::new);
        repo.delete(toDelete);
    }
}
