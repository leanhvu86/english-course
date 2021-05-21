package com.selflearning.englishcourses.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * @author manhnd
 * @version 1.0
 */
@Getter
@Setter
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="roles")
@Document(indexName = "roles", shards = 2)
public class Role {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ROLE_ID", length = 16)
    private UUID id;

    @NonNull
    @Column(name="ROLE_NAME", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

}
