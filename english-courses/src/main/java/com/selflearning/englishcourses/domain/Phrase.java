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
@Table(name="phrases", uniqueConstraints = @UniqueConstraint(columnNames = {"PHRASE_TEXT", "PHRASE_IPA"}))
@Document(indexName = "phrases", shards = 2)
public class Phrase {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "PHRASE_ID", length = 16)
    private UUID id;

    @Column(name="PHRASE_TEXT", unique = true, nullable = false)
    private String text;

    @Column(name="PHRASE_IPA")
    private String ipa;

    @OneToMany(mappedBy = "phrase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhraseDetail> phraseDetails;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;
}
