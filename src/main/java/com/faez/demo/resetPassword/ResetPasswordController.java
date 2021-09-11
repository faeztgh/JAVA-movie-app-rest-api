package com.faez.demo.resetPassword;

import com.faez.demo.exceptions.ApiRequestException;
import com.faez.demo.services.EmailService;
import com.faez.demo.user.User;
import com.faez.demo.user.UserServiceImpl;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static com.faez.demo.routes.ApiRoute.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reset Password")
public class ResetPasswordController {

    private final UserServiceImpl userService;
    private final ResetPasswordServiceImpl resetPasswordService;
    private final EmailService emailService;

    @PostMapping(RESET_PASSWORD_API)
    public ResponseEntity<Map<String, String>> resetPassword(
            @RequestParam("email") String userEmail) throws MessagingException, TemplateException, IOException {
        User user = userService.geUserByEmail(userEmail);
        if (user == null) {
            throw new ApiRequestException("User not found");
        }
        String token = UUID.randomUUID().toString();
        resetPasswordService.createPasswordResetTokenForUser(user, token);
        emailService.sendResetPassword(user, token);

        Map<String, String> response = new HashMap<>();
        response.put("status", "200");
        response.put("message", "Please check your email");
        response.put("timestamp", new Date().toString());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(VALIDATE_RESET_PASSWORD_TOKEN_API)
    public ResponseEntity<Map<String, String>> validateToken(Model model, @RequestParam("token") String token) {
        String result = resetPasswordService.validatePasswordResetToken(token);
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", new Date().toString());
        if (result != null && result.equals("valid")) {
            response.put("status", String.valueOf(OK.value()));
            response.put("message", "Token is Valid");
            ResponseEntity.ok().body(response);
        }
        response.put("status", String.valueOf(HttpStatus.FORBIDDEN.value()));
        response.put("message", "Token is " + result);
        return ResponseEntity.badRequest().body(response);
    }


    @PostMapping(NEW_PASSWORD_API)
    public ResponseEntity<Map<String, String>> savePassword(@Valid @RequestBody ResetPasswordDto passwordDto) {
        if (passwordDto.getToken() == null) throw new ApiRequestException("Please add token");
        if (!passwordDto.getNewPassword().equals(passwordDto.getRepeatPassword()))
            throw new ApiRequestException("Passwords Do Not Match!");

        String result = resetPasswordService.validatePasswordResetToken(passwordDto.getToken());

        if (result != null && result.equals("valid")) {
            Optional<User> user = resetPasswordService.getUserByPasswordResetToken(passwordDto.getToken());
            Map<String, String> response = new HashMap<>();
            if (user.isPresent()) {
                resetPasswordService.changeUserPassword(user.get(), passwordDto.getNewPassword());
                response.put("status", "ok");
                response.put("message", "Password Changed");
                response.put("timestamp", new Date().toString());
                return ResponseEntity.status(OK).body(response);
            } else {
                throw new ApiRequestException("User not exist!");
            }
        } else {
            throw new ApiRequestException("Token is not valid");
        }

    }
}
