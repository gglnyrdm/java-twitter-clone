package com.workintech.twitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = "fsweb")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Users> users;

    public void addUser(Users user){
        if (users == null){
            users = new HashSet<>();
        }
        users.add(user);
    }
}
