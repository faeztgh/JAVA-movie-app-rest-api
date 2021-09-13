package com.faez.movie.services;

import com.faez.movie.user.User;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.faez.movie.utils.constants.AppConfig.RESET_PASSWORD_EXPIRATION;
import static java.lang.System.currentTimeMillis;

@Service
public class EmailService {

    private final Configuration configuration;
    @Autowired
    private JavaMailSender mailSender;

    public EmailService(Configuration configuration) {
        this.configuration = configuration;
    }

    public void sendMail(String receiver, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(receiver);

        msg.setSubject(subject);
        msg.setText(content);

        mailSender.send(msg);
    }


    public void sendWelcomeEmail(User user) throws MessagingException, TemplateException, IOException {
        String subject = "You Are Registered Successfully!";
        String emailContent = getEmailContentForRegister(user);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    public void sendReport(String subject, Map model, String templateName) {

//        String emailContent = getEmailContentForReports();
    }


    public void sendResetPassword(User user, String token) throws MessagingException, TemplateException, IOException {
        String subject = "Reset Password";
        String emailContent = getEmailContentForResetPassword(token);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }


//    private String getEmailContentForReports() {
//        String templateName = "monitoring.ftlh";
//        Map<String, Object> model = new HashMap<>();
//
//
//        return constructTemplate(model, templateName);
//    }

    private String getEmailContentForRegister(User user) {
        String templateName = "welcome-email.ftlh";
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);

        return constructTemplate(model, templateName);
    }

    private String getEmailContentForResetPassword(String token) {
        String templateName = "resetPassword-email.ftlh";
        Map<String, String> model = new HashMap<>();
        model.put("token", token);
        model.put("expirationTime", new Date(currentTimeMillis() + RESET_PASSWORD_EXPIRATION).toString());

        return constructTemplate(model, templateName);
    }

    private String constructTemplate(Object model, String templateName) {
        try {
            StringWriter stringWriter = new StringWriter();
            configuration.getTemplate(templateName).process(model, stringWriter);
            return stringWriter.getBuffer().toString();
        } catch (TemplateException | IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }


}
