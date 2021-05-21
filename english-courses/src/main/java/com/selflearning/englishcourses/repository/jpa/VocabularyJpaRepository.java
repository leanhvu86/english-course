package com.selflearning.englishcourses.repository.jpa;

import com.selflearning.englishcourses.domain.Vocabulary;
import com.selflearning.englishcourses.service.dto.VocabularyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface VocabularyJpaRepository extends JpaRepository<Vocabulary, UUID> {

    @Modifying
    @Query("SELECT v.id as id, v.word as word, v.wordClass as wordClass, v.description as description, v.meaning as meaning, v.imagePath as imagePath " +
            "FROM Vocabulary v JOIN v.word as word JOIN v.wordClass as wordclass")
    List<Vocabulary> findAllVocabularies();

    @Modifying
    @Transactional
    @Query("UPDATE Vocabulary v SET v.imagePath = :imagePath WHERE v.id = :id")
    void updateVocabularyImagePath(@Param("imagePath") String imagePath, UUID id);

    @Query("SELECT v FROM Vocabulary v WHERE v.word.text = :text")
    Vocabulary findByWordText(String text);

}
