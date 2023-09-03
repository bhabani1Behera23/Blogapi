package com.BlogApi.BlogApi.reposittory;

import com.BlogApi.BlogApi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
//24.40