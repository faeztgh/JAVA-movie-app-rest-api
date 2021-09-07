package com.faez.demo.services;

import com.faez.demo.user.User;
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
import java.util.HashMap;
import java.util.Map;

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
        String emailContent = getEmailContent(user);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    private String getEmailContent(User user) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        configuration.getTemplate("welcome-email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
