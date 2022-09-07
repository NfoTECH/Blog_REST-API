package com.fortunate.blogrestfulapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Entity
@ToString
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String role;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        User user = (User) o;
//        return id != null && Objects.equals(id, user.id);
//    }

//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}