package com.faez.demo.resetPassword;

import com.faez.demo.user.User;

import java.util.Optional;

public interface IResetPassword {
    void createPasswordResetTokenForUser(User user, String token);

    Optional<User> getUserByPasswordResetToken(String token);

}
