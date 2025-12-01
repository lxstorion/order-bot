package com.tensei.order_bot.handler.command;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface CommandHandler {

    Command getSupportedCommand();

    List<PartialBotApiMethod<?>> handle(Update update);

}
