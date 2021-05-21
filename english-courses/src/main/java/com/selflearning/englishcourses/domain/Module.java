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
@ToString(exclude = "lessonModules")
@EqualsAndHashCode(exclude = "lessonModules")
@Entity
@Table(name = "modules")
@Document(indexName = "modules", shards = 2)
public class Module {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "MODULE_ID", length = 16)
    private UUID id;

    @Column(name = "MODULE_NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "module")
    private List<LessonModule> lessonModules;

    @Column(name = "CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name = "UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

    public Module() {
    }

    public Module(String name) {
        this.name = name;
    }
}
