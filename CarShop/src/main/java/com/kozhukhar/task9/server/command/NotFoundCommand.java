package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop_online.exception.Messages;

public class NotFoundCommand implements ServerCommand {

    @Override
    public String execute(String parameters) {
        return Messages.COMMAND_WAS_NOT_FOUND;
    }
}
