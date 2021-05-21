package com.selflearning.englishcourses.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "grammar_lessons")
@Document(indexName="grammar_lessons", shards = 2)
public class GrammarLesson {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "GRAMMAR_LESSON_ID", length = 16)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LESSON_MODULE_ID", nullable = false)
    private LessonModule lessonModule;

    @Column(name = "GRAMMAR_TITLE", nullable = false, unique = true)
    private String title;

    @Column(name = "GRAMMAR_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;



}
