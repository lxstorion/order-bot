package com.tensei.order_bot.handler.command;

import lombok.Getter;

@Getter
public enum Command {

    START("/start");

    private String commandText;

    Command(String commandText) {
        this.commandText = commandText;
    }

}
