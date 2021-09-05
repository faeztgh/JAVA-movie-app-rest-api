package com.faez.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.FetchType.EAGER;
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
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    @JsonProperty(access = WRITE_ONLY)
    private String password;

    private String email;

    @ManyToMany(fetch = EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    private String avatar;

    public User(Long id, String firstName, String lastName, String username, String password, String email, Collection<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
