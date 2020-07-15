package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.Purse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurseRepo extends JpaRepository<Purse, Long> {
}
