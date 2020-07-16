package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepo extends JpaRepository<Operation, Long> {
}
