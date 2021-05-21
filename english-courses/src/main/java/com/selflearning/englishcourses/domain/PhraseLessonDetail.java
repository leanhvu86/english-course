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

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="phrase_lesson_details")
@Document(indexName="phrase_lesson_details", shards = 2)
public class PhraseLessonDetail {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "PHRASE_LESSON_DETAIL_ID", length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "PHRASE_LESSON_ID", nullable = false)
    private PhraseLesson phraseLesson;

    @ManyToOne
    @JoinColumn(name="PHRASE_DETAIL_ID", nullable = false)
    private PhraseDetail phraseDetail;

    @ManyToOne
    @JoinColumn(name="SENTENCE_ID", nullable = false)
    private Sentence sentence;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
