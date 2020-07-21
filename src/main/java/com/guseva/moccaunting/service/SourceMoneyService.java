package com.guseva.moccaunting.service;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.SourceMoney;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import com.guseva.moccaunting.repo.SourceMoneyRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceMoneyService {
    private final SourceMoneyRepo sourceMoneyRepo;
    private final OperationRepo operRepo;

    public SourceMoneyService(SourceMoneyRepo sourceMoneyRepo, OperationRepo operRepo) {
        this.sourceMoneyRepo = sourceMoneyRepo;
        this.operRepo = operRepo;
    }

    public List<SourceMoney> list() {
        return sourceMoneyRepo.findAll();
    }

    public SourceMoney getOne(Long id) {
        return sourceMoneyRepo.findById(id).orElseThrow(NotFound::new);
    }

    public OperationPageDto allOperations(Long id, Pageable pageable) {
        SourceMoney sourceMoneyFromDB = sourceMoneyRepo.findById(id).orElseThrow(NotFound::new);

        Page<Operation> page = operRepo.findAllBySourceMoney(sourceMoneyFromDB, pageable);
        return new OperationPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public SourceMoney update(Long id, SourceMoney source) {
        SourceMoney sourceFromDB = sourceMoneyRepo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(source, sourceFromDB, "id");
        return sourceMoneyRepo.save(sourceFromDB);
    }

    public SourceMoney create(SourceMoney source) {
        source.setIsActive(true);
        return sourceMoneyRepo.save(source);
    }

    public void delete(Long id) {
        var toDelete = sourceMoneyRepo.findById(id).orElseThrow(NotFound::new);
        sourceMoneyRepo.delete(toDelete);
    }
}
