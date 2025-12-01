package com.tensei.order_bot.core;

import com.tensei.order_bot.exception.CommandNotSupportedException;
import com.tensei.order_bot.handler.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommandDispatcher {

    private final List<CommandHandler> commandHandlers;

    public List<PartialBotApiMethod<?>> dispatch(Update update) throws CommandNotSupportedException {

        String command = update.getMessage().getText();

        List<PartialBotApiMethod<?>> suitableBotApiMethods = commandHandlers.stream()
                .filter(h -> h.getSupportedCommand().getCommandText().equals(command))
                .map(h -> h.handle(update))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        if (suitableBotApiMethods.isEmpty()) {
            throw new CommandNotSupportedException("Unsupported command: " + command);
        }

        return suitableBotApiMethods;

    }

}
