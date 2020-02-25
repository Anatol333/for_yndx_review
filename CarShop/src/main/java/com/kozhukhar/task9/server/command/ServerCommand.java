package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop_online.exception.AppException;

public interface ServerCommand {

    String execute(String parameters) throws AppException;

}
