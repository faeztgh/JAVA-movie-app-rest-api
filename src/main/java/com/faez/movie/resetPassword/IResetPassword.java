package com.faez.movie.resetPassword;

import com.faez.movie.user.User;

import java.util.Optional;

public interface IResetPassword {
    void createPasswordResetTokenForUser(User user, String token);

    Optional<User> getUserByPasswordResetToken(String token);

}
