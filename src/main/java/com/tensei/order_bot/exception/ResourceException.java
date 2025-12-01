package com.tensei.order_bot.exception;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ResourceException extends TelegramApiException {

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

}
