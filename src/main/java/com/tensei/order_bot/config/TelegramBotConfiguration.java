package com.tensei.order_bot.config;

import com.tensei.order_bot.bot.OrderTelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(
            OrderTelegramBot orderTelegramBot
    ) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(orderTelegramBot);
        return botsApi;
    }

}
