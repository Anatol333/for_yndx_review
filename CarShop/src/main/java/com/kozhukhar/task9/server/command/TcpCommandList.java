package com.kozhukhar.task9.server.command;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.task9.server.client_thread.HttpRes;

import java.util.HashMap;
import java.util.Map;

public class TcpCommandList {

    private Map<String, ServerCommand> commandMap;

    private ServiceContext serviceContext;

    private static final String GET_COUNT = "get_count";
    private static final String GET_ITEM = "get_item";
    private static final String NOT_FOUND = "not_found";

    public TcpCommandList(ServiceContext serviceContext) {
        this.commandMap = new HashMap<>();
        this.serviceContext = serviceContext;
        initCommands();
    }

    private void initCommands() {
        commandMap.put(GET_COUNT, new CountOfProductsCommand(serviceContext.getCatalogService()));
        commandMap.put(GET_ITEM, new GetItemByNameCommand(serviceContext.getCatalogService()));
        commandMap.put(NOT_FOUND, new NotFoundCommand());
    }

    public ServerCommand getCommand(String inputCommand) {
        if (inputCommand == null || !commandMap.containsKey(inputCommand)) {
            return commandMap.get(NOT_FOUND);
        }
        return commandMap.get(inputCommand);
    }
}
