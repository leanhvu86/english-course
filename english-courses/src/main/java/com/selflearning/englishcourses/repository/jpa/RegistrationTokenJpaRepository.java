package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistrationTokenJpaRepository extends JpaRepository<RegistrationToken, UUID> {
}
