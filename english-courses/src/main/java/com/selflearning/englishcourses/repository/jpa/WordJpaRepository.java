package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface WordJpaRepository extends JpaRepository<Word, UUID> {

    Word findByTextAndIpa(String text, String ipa);

    @Transactional
    @Modifying
    @Query("update Word w set w.normalAudioPath = :normalAudioPath, w.specialAudioPath = :specialAudioPath " +
            "where w.id = :id")
    void updateWordAudioPaths(@Param("id") UUID id,
                              @Param("normalAudioPath") String normalAudioPath,
                              @Param("specialAudioPath") String specialAudioPath);

}
