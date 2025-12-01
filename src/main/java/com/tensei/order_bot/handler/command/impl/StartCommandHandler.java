package com.tensei.order_bot.handler.command.impl;

import com.tensei.order_bot.exception.ResourceException;
import com.tensei.order_bot.handler.command.Command;
import com.tensei.order_bot.handler.command.CommandHandler;
import com.tensei.order_bot.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class StartCommandHandler implements CommandHandler {

    @Value("text/start.txt")
    private ClassPathResource textResource;

    @Override
    public Command getSupportedCommand() {
        return Command.START;
    }

    @Override
    public List<PartialBotApiMethod<?>> handle(Update update) {

        try {
            SendMessage method = SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text(ResourceUtil.getResourceContentAsString(textResource))
                    .build();

            return List.of(method);

        } catch (ResourceException e) {
            throw new RuntimeException(e);
        }

    }

}
