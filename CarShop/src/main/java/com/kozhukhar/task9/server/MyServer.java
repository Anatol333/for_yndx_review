package com.kozhukhar.task9.server;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.task9.server.client_thread.produce.AbstractThreadFactory;
import com.kozhukhar.task9.server.client_thread.produce.FactoryProducer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements Runnable {

    private ServiceContext serviceContext;

    private ServerType serverType;

    private Runnable clientRunnable;

    private static final Logger LOG = Logger.getLogger(MyServer.class);
    private int port;

    public MyServer(ServiceContext serviceContext, int port, ServerType serverType) {
        this.serviceContext = serviceContext;
        this.port = port;
        this.serverType = serverType;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOG.info("The Server was started and listening to the port " + port);
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                LOG.info("New client was connected");

                AbstractThreadFactory threadFactory = FactoryProducer.getFactory(serverType);
                clientRunnable = threadFactory.createClientThread(socket, serviceContext);
                new Thread(clientRunnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Server was interrupted.");
    }
}
