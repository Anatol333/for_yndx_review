package com.kozhukhar.task9.server.client_thread.produce;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.task9.server.client_thread.HttpClientThread;
import java.net.Socket;

public class ClientHttpFactory extends AbstractThreadFactory {
    @Override
    public Runnable createClientThread(Socket socket, ServiceContext serviceContext) {
        return new HttpClientThread(socket, serviceContext);
    }
}
