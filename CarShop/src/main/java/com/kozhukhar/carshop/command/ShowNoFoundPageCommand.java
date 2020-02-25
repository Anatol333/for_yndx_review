package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.view.ConsoleView;
import org.apache.log4j.Logger;

public class ShowNoFoundPageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ShowNoFoundPageCommand.class);

    @Override
    public String execute() {
        LOG.debug("Showing NoFound page");
        return ConsoleView.ERROR_404;
    }
}
