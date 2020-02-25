package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop_online.exception.AppException;

/**
 * Main command interface.
 * This interface provides command method for execution some command
 */
public interface Command {

    String execute() throws AppException;

}
