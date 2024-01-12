package com.workintech.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_role", schema = "fsweb")
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authority")
    private String authority;

    @OneToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Users> users;

    public void addUser(Users user){
        if (users == null){
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
