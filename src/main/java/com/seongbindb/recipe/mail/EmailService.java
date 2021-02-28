package com.seongbindb.recipe.mail;

import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(EmailMessage emailMessage);
}
