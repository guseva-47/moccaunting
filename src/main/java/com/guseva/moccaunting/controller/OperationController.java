package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.exception.BadRequest;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("operation")
public class OperationController {
    private final OperationRepo repo;
    
    @Autowired
    public OperationController(OperationRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Operation> list() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Operation getOne(@PathVariable Long id){
        return repo.findById(id).orElseThrow(NotFound::new);
    }

    @PutMapping("{id}")
    public Operation update(@PathVariable Long id, @RequestBody Operation operation) {
        Operation operationFromDB = repo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(operation, operationFromDB, "id");
        return repo.save(operationFromDB);
    }

    @PostMapping
    public Operation create(@RequestBody Operation op) {
        // Возможны операции:
        //                       -> источник(sourceMoney)
        // источник(sourceMoney) -> кошелек(purse)
        // кошелек(purse)        -> категория трат(categoryOutlay)
        // кошелек(purse)        -> кошелек(purseSponsor)

        Object[] list = {op.getSourceMoney(), op.getPurse(), op.getPurseSponsor(), op.getCategoryOutlay()};
        int countNotNull = (int)Arrays.stream(list).filter(Objects::nonNull).count();
        boolean isGoodRequest = false;

        switch (countNotNull) {
            case 1:
                isGoodRequest = (op.getSourceMoney() != null);
                break;
            case 2:
                if (op.getPurse() != null)
                    isGoodRequest = (op.getSourceMoney() != null || op.getCategoryOutlay() != null || op.getPurseSponsor() != null);
        }
        if (!isGoodRequest)
            throw new BadRequest();

        return repo.save(op);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        var toDelete = repo.findById(id).orElseThrow(NotFound::new);
        repo.delete(toDelete);
    }
}
