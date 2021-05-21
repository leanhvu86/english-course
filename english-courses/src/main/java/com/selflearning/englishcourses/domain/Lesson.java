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
@ToString(exclude = {"lessonModules"})
@EqualsAndHashCode(exclude = {"lessonModules"})
@Entity
@Table(name="lessons", uniqueConstraints = @UniqueConstraint(columnNames = {"COURSE_ID", "LESSON_ORDER_NUMBER"}))
@Document(indexName = "lessons", shards = 2)
public class Lesson {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "LESSON_ID", length = 16)
    private UUID id;

    @Column(name="LESSON_ORDER_NUMBER", nullable = false)
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name="COURSE_ID", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<LessonModule> lessonModules;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
