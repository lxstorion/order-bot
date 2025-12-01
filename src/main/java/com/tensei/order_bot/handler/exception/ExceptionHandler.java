package com.tensei.order_bot.handler.exception;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public interface ExceptionHandler {

    <T extends TelegramApiException> List<PartialBotApiMethod<?>> handleException(T e, Update update) throws TelegramApiException;

}
