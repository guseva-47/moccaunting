package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepo extends JpaRepository<Operation, Long> {
    Page<Operation> findAllByTags (Tag tag, Pageable pageable);
    Page<Operation> findAllByCategoryOutlay (CategoryOutlay categoryOutlay, Pageable pageable);
    Page<Operation> findAllByPurse (Purse purse, Pageable pageable);
    Page<Operation> findAllBySourceMoney (SourceMoney sourceMoney, Pageable pageable);
    Page<Operation> findAll(Pageable pageable);
}
