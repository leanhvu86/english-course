package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface SentenceJpaRepository extends JpaRepository<Sentence, UUID> {

    @Modifying
    @Transactional
    @Query("update Sentence s set s.audioPath = :audioPath where s.id = :id")
    void updateSentenceAudioPath(@Param("audioPath") String audioPath, @Param("id") UUID id);

    @Query("select s from Sentence s where length(trim(s.audioPath)) > 0")
    List<Sentence> findAllSentencesWhereAudioPathIsNotEmpty();

    Sentence findByText(String text);

}
