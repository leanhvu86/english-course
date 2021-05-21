package com.selflearning.englishcourses.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author manhnd
 * @version 1.0
 */
@Getter
@Setter
@ToString(exclude = {"userCourses", "forgetPasswordTokens", "registrationTokens"})
@EqualsAndHashCode(exclude = {"userCourses", "forgetPasswordTokens", "registrationTokens"})
@Entity
@Table(name = "users")
@Document(indexName = "users", shards = 2)
public class User implements UserDetails {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "USER_ID", length = 16)
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "USER_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false)
    )
    private List<Role> roles;

    @Column(name = "USERNAME", unique = true, length = 45, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true, length = 80, nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", length = 45, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 45, nullable = false)
    private String lastName;

    @Column(name = "GENDER", nullable = false)
    private Boolean gender;

    @Column(name = "ENABLED", columnDefinition = "BIT(1) DEFAULT 0b00")
    private Boolean enabled;

    @Column(name = "LOCKED", columnDefinition = "BIT(1) DEFAULT 0b00")
    private Boolean locked;

    @OneToMany(mappedBy = "user")
    private List<UserCourse> userCourses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ForgetPasswordToken> forgetPasswordTokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RegistrationToken> registrationTokens;

    @Column(name="CREATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Column(name="UPDATED_TIME", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
