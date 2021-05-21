package com.selflearning.englishcourses.domain;

import lombok.*;
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
@ToString(exclude = {"lessons", "userCourses"})
@EqualsAndHashCode(exclude = {"lessons", "userCourses"})
@Entity
@Table(name = "courses")
@Document(indexName = "courses", shards = 2)
public class Course {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "COURSE_ID", length = 16)
    private UUID id;

    @Column(name = "COURSE_NAME", nullable = false, unique = true)
    private String name;

    @Column(name="COURSE_DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<UserCourse> userCourses;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

}
