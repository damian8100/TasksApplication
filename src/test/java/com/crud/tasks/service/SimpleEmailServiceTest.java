package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

@Test
public void schuldSendEmail() {
    //given
    Mail mail = new Mail("mail@test.pl", "ttt","fff","dffd");

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(mail.getMailTo());
    mailMessage.setSubject(mail.getSubject());
    mailMessage.setSubject(mail.getMessage());

    //when

    simpleEmailService.send(mail);

    //then
   verify(javaMailSender,times(1)).send(any(SimpleEmailService.class));

}}