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
@Table(name="vocabulary_quiz_choice_answers")
@Document(indexName = "vocabulary_quiz_choice_answers", shards = 2)
public class VocabularyQuizChoiceAnswer {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "VOCABULARY_QUIZ_CHOICE_ANSWER_ID", length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="VOCABULARY_QUIZ_CHOICE_QUESTION_ID", nullable = false)
    private VocabularyQuizChoiceQuestion vocabularyQuizChoiceQuestion;

    @Column(name="VOCABULARY_QUIZ_CHOICE_ANSWER", nullable = false)
    private String answer;

    @Column(name="IS_RIGHT_ANSWER", nullable = false)
    private Boolean rightAnswer;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
