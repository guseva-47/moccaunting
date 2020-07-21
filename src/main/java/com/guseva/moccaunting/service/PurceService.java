package com.guseva.moccaunting.service;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Purse;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import com.guseva.moccaunting.repo.PurseRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurceService {
    private final PurseRepo purseRepo;
    private final OperationRepo operRepo;

    @Autowired
    public PurceService(PurseRepo purseRepo, OperationRepo operRepo) {
        this.purseRepo = purseRepo;
        this.operRepo = operRepo;
    }

    // todo
    public List<Purse> list() {
        return purseRepo.findAll();
    }

    public Purse getOne(Long id) {
        return  purseRepo.findById(id).orElseThrow(NotFound::new);
    }

    // Постраничный вывод операций
    public OperationPageDto allOperations(Long id, Pageable pageable) {
        Purse purseFromDB = purseRepo.findById(id).orElseThrow(NotFound::new);

        Page<Operation> page = operRepo.findAllByPurse(purseFromDB, pageable);
        return new OperationPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public Purse update(Long id, Purse purse) {
        Purse purseFromDB = purseRepo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(purse, purseFromDB, "id");
        return purseRepo.save(purseFromDB);
    }

    public Purse create(Purse purse) {
        purse.setIsActive(true);
        return purseRepo.save(purse);
    }

    public void delete(Long id) {
        var toDelete = purseRepo.findById(id).orElseThrow(NotFound::new);
        purseRepo.delete(toDelete);
    }
}
