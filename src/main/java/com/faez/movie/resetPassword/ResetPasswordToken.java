package com.faez.movie.resetPassword;

import com.faez.movie.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "password_reset_token")
@Entity
@RequiredArgsConstructor
@Getter
public class ResetPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public ResetPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = new Date();
    }
}