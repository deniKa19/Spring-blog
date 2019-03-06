package com.codeup.blog.models;

import com.codeup.blog.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Users userDao;


    @Value("$spring.mail.from")
    private String from;

    public void prepareAndSend(Post post, String subject, String body) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userDao.findByUsername(username);

        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(body);

        try{
            this.emailSender.send(message);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
