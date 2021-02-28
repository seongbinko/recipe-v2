package com.seongbindb.recipe.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("local")
@Component
public class ConsoleEmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        log.info("sent email: {}" , emailMessage.getMessage());
    }
}
