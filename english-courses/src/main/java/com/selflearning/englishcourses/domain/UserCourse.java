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
@ToString(exclude = {"lessonModuleMarks"})
@EqualsAndHashCode(exclude = {"lessonModuleMarks"})
@Entity
@Table(name = "users_courses")
@Document(indexName = "users_courses", shards = 2)
public class UserCourse {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "USER_COURSE_ID", length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    @Column(name = "JOINED_TIME", nullable = false)
    private Date joinedTime;

    @OneToMany(mappedBy = "userCourse")
    private List<LessonModuleMark> lessonModuleMarks;

}
