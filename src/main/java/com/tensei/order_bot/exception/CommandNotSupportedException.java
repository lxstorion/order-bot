package com.tensei.order_bot.exception;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CommandNotSupportedException extends TelegramApiException {

    public CommandNotSupportedException(String message) {
        super(message);
    }

    public CommandNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

}
