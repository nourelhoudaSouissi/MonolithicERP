package com.csidigital.management.service;

import com.csidigital.dao.entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
