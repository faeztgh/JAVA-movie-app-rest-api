package com.faez.demo.user;

import com.faez.demo.movie.Movie;
import com.faez.demo.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

/**
 * @author unknown
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Getter
@Setter
@Table(
        name = "user",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_username_unique", columnNames = "username"),
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String username;

    @JsonProperty(access = WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    private String avatar;

    @ManyToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(fetch = LAZY)
    @ToString.Exclude
    private List<Movie> favoriteMovies = new ArrayList<>();

    public User(Long id, String firstName, String lastName, String username, String password, String email, Collection<Role> roles, List<Movie> favoriteMovies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.favoriteMovies = favoriteMovies;
    }
}
