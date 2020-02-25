package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.view.ConsoleView;
import org.apache.log4j.Logger;

public class ShowHomePageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ShowHomePageCommand.class);

    @Override
    public String execute() {
        LOG.debug("Showing home page");
        return ConsoleView.MAIN_PAGE;
    }
}
