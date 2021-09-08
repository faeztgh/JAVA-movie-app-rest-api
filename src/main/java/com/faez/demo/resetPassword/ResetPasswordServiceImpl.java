package com.faez.demo.resetPassword;

import com.faez.demo.user.User;
import com.faez.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.faez.demo.utils.constants.AppConfig.RESET_PASSWORD_EXPIRATION;

@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements IResetPassword {

    private final ResetPasswordRepository resetPasswordRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        ResetPasswordToken myToken = new ResetPasswordToken(token, user);
        resetPasswordRepository.save(myToken);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.of(resetPasswordRepository.findByToken(token).getUser());
    }


    public String validatePasswordResetToken(String token) {
        final ResetPasswordToken passToken = resetPasswordRepository.findByToken(token);

        if (isTokenFound(passToken) && !isTokenExpired(passToken)) return "valid";
        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(ResetPasswordToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(ResetPasswordToken passToken) {
        return System.currentTimeMillis() > passToken.getExpiryDate().getTime() + RESET_PASSWORD_EXPIRATION;
    }


    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
