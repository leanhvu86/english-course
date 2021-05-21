package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(String name);

}
