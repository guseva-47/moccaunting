package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("operation")
public class OperationController {
    private final OperationService operationService;
    
    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public OperationPageDto list(
            @PageableDefault(size = OperationPageDto.PAGE_SIZE, sort = {"operationDate", "id"}) Pageable pageable
    ) {
        return operationService.list(pageable);
    }

    @GetMapping("{id}")
    public Operation getOne(@PathVariable Long id){
        return operationService.getOne(id);
    }

    @PutMapping("{id}")
    public Operation update(@PathVariable Long id, @RequestBody Operation operation) {
        return operationService.update(id, operation);
    }

    @PostMapping
    public Operation create(@RequestBody Operation op) {
        return operationService.create(op);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        operationService.delete(id);
    }
}
