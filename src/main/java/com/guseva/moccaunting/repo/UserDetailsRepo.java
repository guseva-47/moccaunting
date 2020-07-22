package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
