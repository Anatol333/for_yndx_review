package com.kozhukhar.task9.server.client_thread.produce;

import com.kozhukhar.carshop.context.ServiceContext;

import java.net.Socket;

public abstract class AbstractThreadFactory {

    public abstract Runnable createClientThread(Socket socket, ServiceContext serviceContext);

}
