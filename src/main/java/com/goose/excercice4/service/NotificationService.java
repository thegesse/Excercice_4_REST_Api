package com.goose.excercice4.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public void notifier(String destinataire, String message);
}
