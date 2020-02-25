package com.kozhukhar.task9;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

public class SocketHolder implements Closeable {

    private final ServerSocket socket;

    public SocketHolder(ServerSocket socket) {
        this.socket = socket;
    }

    public ServerSocket getSocket() {
        return socket;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    public static int findFreePort() throws IOException {
        try (SocketHolder holder = new SocketHolder(new ServerSocket(0))) {
            holder.getSocket().setReuseAddress(true);
            int port = holder.getSocket().getLocalPort();
            return port;
        }
    }
}
