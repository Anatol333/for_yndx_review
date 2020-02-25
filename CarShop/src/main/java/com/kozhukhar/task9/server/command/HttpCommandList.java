package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.task9.server.client_thread.HttpRes;

import java.util.HashMap;
import java.util.Map;

public class HttpCommandList {

    private Map<String, ServerCommand> commandMap;

    private ServiceContext serviceContext;

    private static final String NOT_FOUND = "not_found";

    public HttpCommandList(ServiceContext serviceContext) {
        this.commandMap = new HashMap<>();
        this.serviceContext = serviceContext;
        initCommands();
    }

    private void initCommands() {
        commandMap.put(HttpRes.COUNT_COMMAND, new CountOfProductsJsonCommand(serviceContext.getCatalogService()));
        commandMap.put(HttpRes.GET_INFO_COMMAND, new GetItemByNameJsonCommand(serviceContext.getCatalogService()));
        commandMap.put(NOT_FOUND, new NotFoundCommand());
    }

    public ServerCommand getCommand(String inputCommand) {
        if (inputCommand == null || !commandMap.containsKey(inputCommand)) {
            return commandMap.get(NOT_FOUND);
        }
        return commandMap.get(inputCommand);
    }

}
