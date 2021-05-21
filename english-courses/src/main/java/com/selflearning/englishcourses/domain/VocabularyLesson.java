package com.selflearning.englishcourses.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * @author manhnd
 * @version 1.0
 */
@Getter
@Setter
@ToString(exclude = {"vocabularyLessonDetails", "vocabularyQuizChoices"})
@EqualsAndHashCode(exclude = {"vocabularyLessonDetails", "vocabularyQuizChoices"})
@Entity
@Table(name="vocabulary_lessons")
@Document(indexName = "vocabulary_lessons", shards = 2)
public class VocabularyLesson {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "VOCABULARY_LESSON_ID", length = 16)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "LESSON_MODULE_ID", nullable = false)
    private LessonModule lessonModule;

    @OneToMany(mappedBy = "vocabularyLesson", cascade = CascadeType.ALL)
    private List<VocabularyLessonDetail> vocabularyLessonDetails;

    @OneToMany(mappedBy = "vocabularyLesson", cascade = CascadeType.ALL)
    private List<VocabularyQuizChoice> vocabularyQuizChoices;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
