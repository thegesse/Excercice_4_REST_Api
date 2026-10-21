package com.goose.excercice4.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ConsoleNotificationService implements NotificationService{
    public void notifier(String destinataire, String message){};
}
