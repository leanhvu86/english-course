package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.WordClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WordClassJpaRepository extends JpaRepository<WordClass, UUID> {

    WordClass findByName(String name);

}
