package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleJpaRepository extends JpaRepository<Module, UUID> {

    Optional<Module> findByName(String name);

    @Query("SELECT m FROM Module m WHERE m.id NOT IN (:ids)")
    List<Module> findAllModulesNotIn(@Param("ids") List<UUID> ids);
}
