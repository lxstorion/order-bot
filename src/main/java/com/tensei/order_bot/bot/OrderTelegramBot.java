package com.tensei.order_bot.bot;

import com.tensei.order_bot.core.CommandDispatcher;
import com.tensei.order_bot.handler.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class OrderTelegramBot extends TelegramLongPollingBot {

    private final CommandDispatcher commandDispatcher;
    private final ExceptionHandler exceptionHandler;

    public OrderTelegramBot(
            @Value("${telegram.bot.token}") String token,
            CommandDispatcher commandDispatcher,
            ExceptionHandler exceptionHandler
    ) {
        super(token);
        this.commandDispatcher = commandDispatcher;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String message = update.getMessage().getText();
        if (message.startsWith("/")) {

            try {

                List<PartialBotApiMethod<?>> methods = commandDispatcher.dispatch(update);
                executeMethods(methods);

            } catch (TelegramApiException e) {
                handleException(e, update);
            }

        }

    }

    @Override
    public String getBotUsername() {
        return "Order Bot";
    }

    private void executeMethods(List<PartialBotApiMethod<?>> methods) throws TelegramApiException {

        for (PartialBotApiMethod<?> method : methods) {

            if (method instanceof BotApiMethod<?> botApiMethod) {
                sendApiMethod(botApiMethod);
            }
            else if (method instanceof SendPhoto sendPhoto) {
                execute(sendPhoto);
            }

        }

    }

    /**
     * Global exception handling point
     *
     * @param e any exception which either is an instance of {@code TelegramApiException} or instance of type that extends abovementioned class
     * @param update updated user data
     * @param <T>
     */
    private <T extends TelegramApiException> void handleException(T e, Update update) {

        try {
            List<PartialBotApiMethod<?>> errorMethods = exceptionHandler.handleException(e, update);
            executeMethods(errorMethods);
        } catch (TelegramApiException ex) {

            throw new RuntimeException("Fatal error while execute suitable error response methods", ex);

        }

    }

}
