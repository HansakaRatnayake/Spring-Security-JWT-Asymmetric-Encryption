package com.lezord.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.lezord.app.util.BaseEntity;

import java.util.List;

@Setter
@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLE")
public class Role extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
