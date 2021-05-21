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
//@ToString(exclude = "vocabularies")
//@EqualsAndHashCode(exclude = "vocabularies")
@Entity
@Table(name="words",
        uniqueConstraints = @UniqueConstraint(name = "WORD_UNIQUE", columnNames = {"WORD_TEXT", "WORD_IPA"}))
@Document(indexName = "words", shards = 2)
public class Word {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "WORD_ID", length = 16)
    private UUID id;

    @Column(name="WORD_TEXT", length = 80, nullable = false)
    private String text;

    @Column(name="WORD_IPA", length = 80)
    private String ipa;

    @Column(name="WORD_NORMAL_AUDIO_PATH", length = 1000)
    private String normalAudioPath;

    @Column(name="WORD_SPECIAL_AUDIO_PATH", length = 1000)
    private String specialAudioPath;

//    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
//    private List<Vocabulary> vocabularies;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

}
