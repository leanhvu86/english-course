package com.selflearning.englishcourses.domain;

import lombok.Getter;
import lombok.Setter;
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
//@ToString(exclude = "vocabularies")
//@EqualsAndHashCode(exclude = "vocabularies")
@Entity
@Table(name="word_classes")
@Document(indexName = "word_classes", shards = 2)
public class WordClass {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "WORD_CLASS_ID", length = 16)
    private UUID id;

    @Column(name="WORD_CLASS_NAME", length = 80, nullable = false, unique = true)
    private String name;

    @Column(name="WORD_CLASS_MEANING", length = 80, nullable = false)
    private String meaning;

    @Column(name="WORD_CLASS_DESCRIPTION", length = 1000)
    private String description;

//    @OneToMany(mappedBy = "wordClass", cascade = CascadeType.ALL)
//    private List<Vocabulary> vocabularies;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
