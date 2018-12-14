package com.sev4ikwasd.bike_quest.domain.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class RestUser implements Serializable {

    @Id
    @Type(type = "pg-uuid")
    @Column(name = "user_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Collection<Quest> createdQuests;

    @OneToMany(mappedBy = "passer", cascade = CascadeType.ALL)
    private Collection<PassedQuest> passedQuest;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;
}
