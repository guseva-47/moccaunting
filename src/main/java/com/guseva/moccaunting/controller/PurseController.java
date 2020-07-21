package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Purse;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.service.PurceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purse")
public class PurseController {

    private final PurceService purceService;
    @Autowired
    public PurseController(PurceService purceService) {
        this.purceService = purceService;
    }

    @GetMapping
    public List<Purse> list() {
        return purceService.list();
    }

    @GetMapping("{id}")
    public Purse getOne(@PathVariable Long id) {
        return purceService.getOne(id);
    }

    @GetMapping("{id}/operations")
    public OperationPageDto allOperations(
            @PathVariable Long id,
            @PageableDefault(size = OperationPageDto.PAGE_SIZE, sort = {"operationDate", "id"}) Pageable pageable
    ) {
        return purceService.allOperations(id, pageable);
    }

    @PutMapping("{id}")
    public Purse update(@PathVariable Long id, @RequestBody Purse purse) {
        return purceService.update(id, purse);
    }

    @PostMapping
    public Purse create(@RequestBody Purse purse) {
        return purceService.create(purse);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        purceService.delete(id);
    }
}
