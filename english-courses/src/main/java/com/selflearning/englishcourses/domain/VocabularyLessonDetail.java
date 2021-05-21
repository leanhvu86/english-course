package com.selflearning.englishcourses.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
/**
 * @author manhnd
 * @version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="vocabulary_lesson_details")
@Document(indexName = "vocabulary_lesson_details", shards = 2)
public class VocabularyLessonDetail {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "VOCABULARY_LESSON_DETAIL_ID", length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "VOCABULARY_ID", nullable = false)
    private Vocabulary vocabulary;

    @ManyToOne
    @JoinColumn(name = "SENTENCE_ID", nullable = false)
    private Sentence sentence;

    @ManyToOne
    @JoinColumn(name = "VOCABULARY_LESSON_ID", nullable = false)
    private VocabularyLesson vocabularyLesson;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
