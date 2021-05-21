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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "lesson_module_marks")
@Document(indexName = "lesson_module_marks", shards = 2)
public class LessonModuleMark {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "LESSON_MODULE_MARK_ID", length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_COURSE_ID", nullable = false)
    private UserCourse userCourse;

    @ManyToOne
    @JoinColumn(name = "LESSON_MODULE_ID", nullable = false)
    private LessonModule lessonModule;

    @Column(name="MARK", nullable = false)
    private Float mark;

    @Column(name="CREATED_TIME", nullable = false)
    private Date createdTime;

}
