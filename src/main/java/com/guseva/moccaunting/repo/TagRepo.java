package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}
