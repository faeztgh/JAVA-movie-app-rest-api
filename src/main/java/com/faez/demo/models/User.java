package com.faez.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "user_username_unique", columnNames = "username")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    @ManyToMany(fetch = EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();


}
