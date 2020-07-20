package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepo extends JpaRepository<Operation, Long> {
    List<Operation> findAllByTags (Tag tag);
    List<Operation> findAllByCategoryOutlay (CategoryOutlay category);
    List<Operation> findAllByPurse (Purse purse);
    List<Operation> findAllBySourceMoney (SourceMoney sourceMoney);
}
