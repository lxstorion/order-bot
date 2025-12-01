package com.tensei.order_bot.handler.exception.impl;

import com.tensei.order_bot.exception.CommandNotSupportedException;
import com.tensei.order_bot.handler.exception.ExceptionHandler;
import com.tensei.order_bot.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class CommonGlobalExceptionHandler implements ExceptionHandler {

    @Value("text/unsupported_cmd.txt")
    private ClassPathResource unsupportedCmdTextResource;

    @Override
    public <T extends TelegramApiException> List<PartialBotApiMethod<?>> handleException(T e, Update update) throws TelegramApiException {

        String errorMessage = null;

        if (e instanceof CommandNotSupportedException) {
            errorMessage = ResourceUtil.getResourceContentAsString(unsupportedCmdTextResource);
        }

        SendMessage sendErrorMessageMethod = SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(errorMessage)
                .build();

        return List.of(sendErrorMessageMethod);

    }
}
