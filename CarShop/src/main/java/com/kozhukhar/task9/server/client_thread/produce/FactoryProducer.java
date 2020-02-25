package com.kozhukhar.task9.server.client_thread.produce;

import com.kozhukhar.task9.server.ServerType;

public class FactoryProducer {

    public static AbstractThreadFactory getFactory(ServerType serverType) {
        AbstractThreadFactory factory = null;
        if (serverType.equals(ServerType.TCP)) {
            factory = new ClientTcpFactory();
        } else if (serverType.equals(ServerType.HTTP)) {
            factory = new ClientHttpFactory();
        }
        return factory;
    }

}
