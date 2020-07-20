package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepo extends JpaRepository<Operation, Long> {
    List<Operation> findAllByTags (Tag tag);
}
