package com.guseva.moccaunting.service;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.exception.BadRequest;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class OperationService {
    private final OperationRepo operRepo;
    private final PeriodService periodService;

    @Autowired
    public OperationService(OperationRepo operRepo, PeriodService periodService) {
        this.operRepo = operRepo;
        this.periodService = periodService;
    }

    // Постраничный вывод операций
    public OperationPageDto list(Pageable pageable) {
        Page<Operation> page = operRepo.findAll(pageable);
        return new OperationPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public Operation getOne(Long id) {
        return operRepo.findById(id).orElseThrow(NotFound::new);
    }

    public Operation update(Long id, Operation operation) {
        Operation operationFromDB = operRepo.findById(id).orElseThrow(NotFound::new);

        periodService.deleteOperation(operationFromDB);
        BeanUtils.copyProperties(operation, operationFromDB, "id");

        periodService.addOperation(operationFromDB);
        return operRepo.save(operationFromDB);
    }

    public Operation create(Operation op) {
        // Возможны операции:
        //      TODO                 -> источник(sourceMoney)
        // источник(sourceMoney) -> кошелек(purse)
        // кошелек(purse)        -> категория трат(categoryOutlay)
        // кошелек(purse)        -> кошелек(purseSponsor)

        Object[] list = {op.getSourceMoney(), op.getPurse(), op.getPurseSponsor(), op.getCategoryOutlay()};
        int countNotNull = (int) Arrays.stream(list).filter(Objects::nonNull).count();
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

        //todo
        // if(op.getOperationDate() == null) op.setOperationDate((String) LocalDate.now());

        periodService.addOperation(op);
        return operRepo.save(op);
    }

    public void delete(Long id) {
        var toDelete = operRepo.findById(id).orElseThrow(NotFound::new);
        periodService.deleteOperation(toDelete);
        operRepo.delete(toDelete);
    }
}
