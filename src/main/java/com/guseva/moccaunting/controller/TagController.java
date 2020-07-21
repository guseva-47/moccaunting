package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Tag;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {
    private final TagService tagService;
    
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> list() {
        return tagService.list();
    }

    @GetMapping("{id}")
    public Tag getOne(@PathVariable Long id){
        return tagService.getOne(id);
    }
    
    @PutMapping("{id}")
    public Tag update(@PathVariable Long id, @RequestBody Tag tag) {
        return tagService.update(id, tag);
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return  tagService.create(tag);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }

    // Получить все операции по тегу.
    @GetMapping("{id}/operations")
    public OperationPageDto allOperations(
            @PathVariable Long id,
            @PageableDefault(size = OperationPageDto.PAGE_SIZE, sort = {"operationDate", "id"}) Pageable pageable
    ) {
        return tagService.allOperations(id, pageable);
    }
}
