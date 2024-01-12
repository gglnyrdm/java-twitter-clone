package com.workintech.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "fsweb")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Roles role;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweets> tweets;

    @OneToMany(mappedBy = "followingId", cascade = CascadeType.ALL)
    private Set<Follows> followingId;

    @OneToMany(mappedBy = "followersId", cascade = CascadeType.ALL)
    private Set<Follows> followersId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> comments;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_role_id")
    private UserRole authorities;

    public void addTweet(Tweets tweet){
        if (tweets == null) {
            tweets = new ArrayList<>();
        }
        tweets.add(tweet);
    }

    public void addFollowing(Follows following){
        if (this.followingId == null){
            this.followingId = new HashSet<>();
        }
        this.followingId.add(following);
    }

    public void addFollower(Follows follower){
        if (followersId == null) {
            followersId = new HashSet<>();
        }
        followersId.add(follower);
    }

    public void addUserComment(Comments comment){
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> authorities = new ArrayList<>();
        authorities.add(this.authorities);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
