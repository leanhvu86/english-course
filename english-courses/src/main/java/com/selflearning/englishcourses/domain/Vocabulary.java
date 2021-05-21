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
@Table(name="vocabularies")
@Document(indexName = "vocabularies", shards = 2)
public class Vocabulary {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "VOCABULARY_ID", length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="WORD_ID", nullable = false)
    private Word word;

    @ManyToOne
    @JoinColumn(name="WORD_CLASS_ID", nullable = false)
    private WordClass wordClass;

    @Column(name="VOCABULARY_DESCRIPTION", length = 500)
    private String description;

    @Column(name="VOCABULARY_MEANING")
    private String meaning;

    @Column(name="VOCABULARY_IMAGE_PATH")
    private String imagePath;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
