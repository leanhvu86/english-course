package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.RegistrationToken;
import com.selflearning.englishcourses.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMailActivateAccount(RegistrationToken registerToken) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setSubject("Kích hoạt tài khoản");
            mimeMessageHelper.setTo(registerToken.getUser().getEmail());
            mimeMessageHelper.setText(String.format("%s/activate-account?registration-token=%s", "http://localhost:8080", registerToken.getId().toString()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

}
