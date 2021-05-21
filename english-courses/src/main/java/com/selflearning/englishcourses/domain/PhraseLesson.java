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

@Getter
@Setter
@ToString(exclude = {"phraseLessonDetails"})
@EqualsAndHashCode(exclude = {"phraseLessonDetails"})
@Entity
@Table(name="phrase_lessons")
@Document(indexName="phrase_lessons", shards = 2)
public class PhraseLesson {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "PHRASE_LESSON_ID", length = 16)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LESSON_MODULE_ID", nullable = false)
    private LessonModule lessonModule;

    @OneToMany(mappedBy = "phraseLesson")
    private List<PhraseLessonDetail> phraseLessonDetails;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
