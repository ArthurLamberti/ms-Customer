package com.arthurlamberti.cdb.exceptions;


import com.arthurlamberti.cdb.validation.handler.Notification;

public class NotificationException extends DomainException {
    public NotificationException(String message, final Notification notification) {
        super(message, notification.getErrors());
    }
}
