package com.kozhukhar.task9.server.client_thread.produce;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.task9.server.client_thread.TcpClientThread;

import java.net.Socket;

public class ClientTcpFactory extends AbstractThreadFactory {
    @Override
    public Runnable createClientThread(Socket socket, ServiceContext serviceContext) {
        return new TcpClientThread(socket, serviceContext);
    }
}
