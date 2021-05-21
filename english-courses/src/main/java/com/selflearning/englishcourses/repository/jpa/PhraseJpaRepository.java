package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhraseJpaRepository extends JpaRepository<Phrase, UUID> {
}
