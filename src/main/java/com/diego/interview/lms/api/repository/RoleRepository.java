package com.diego.interview.lms.api.repository;

import com.diego.interview.lms.api.model.Role;
import com.diego.interview.lms.api.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);
}
