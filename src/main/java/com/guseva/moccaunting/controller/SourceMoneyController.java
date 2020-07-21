package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.SourceMoney;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.service.SourceMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("source_money")
public class SourceMoneyController {
    private final SourceMoneyService sourceMoneyService;

    @Autowired
    public SourceMoneyController(SourceMoneyService sourceMoneyService) {
        this.sourceMoneyService = sourceMoneyService;
    }

    @GetMapping
    public List<SourceMoney> list() {
        return sourceMoneyService.list();
    }

    @GetMapping("{id}")
    public SourceMoney getOne(@PathVariable Long id){
        return sourceMoneyService.getOne(id);
    }

    @GetMapping("{id}/operations")
    public OperationPageDto allOperations(
            @PathVariable Long id,
            @PageableDefault(size = OperationPageDto.PAGE_SIZE, sort = {"operationDate", "id"}) Pageable pageable
    ) {
        return sourceMoneyService.allOperations(id, pageable);
    }

    @PutMapping("{id}")
    public SourceMoney update(@PathVariable Long id, @RequestBody SourceMoney source) {
        return sourceMoneyService.update(id, source);
    }

    @PostMapping
    public SourceMoney create(@RequestBody SourceMoney source) {
        return sourceMoneyService.create(source);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        sourceMoneyService.delete(id);
    }
}
