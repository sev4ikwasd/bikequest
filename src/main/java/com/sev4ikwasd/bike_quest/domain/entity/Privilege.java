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
@Table(name = "privileges")
public class Privilege implements Serializable {

    @Id
    @Type(type = "pg-uuid")
    @Column(name = "privilege_id")
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}