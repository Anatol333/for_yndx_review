package com.kozhukhar.task9.server.client_thread;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.task9.server.command.ServerCommand;
import com.kozhukhar.task9.server.command.TcpCommandList;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class TcpClientThread implements Runnable {

    private Socket socket;

    private ServiceContext serviceContext;

    private static final Logger LOG = Logger.getLogger(TcpClientThread.class);

    public TcpClientThread(Socket socket, ServiceContext serviceContext) {
        this.socket = socket;
        this.serviceContext = serviceContext;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String clientSentence = inFromClient.readLine();
            String[] queryParameters = clientSentence.split("=");

            String query = queryParameters[0];
            String parameter = "";
            processRequest(outToClient, queryParameters, query, parameter);

        } catch (IOException ex) {
            LOG.info(Messages.IO_EXCEPTION + ex.getMessage());
        } catch (AppException ex) {
            LOG.info(Messages.COMMAND_COULD_NOT_BE_RECEIVED + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                LOG.info(Messages.IO_EXCEPTION + ex.getMessage());
            }
        }
    }

    private void processRequest(PrintWriter outToClient, String[] queryParameters, String query, String parameter) throws AppException {
        if (queryParameters.length > 1) {
            parameter = queryParameters[1];
        }

        TcpCommandList httpCommandList = new TcpCommandList(serviceContext);
        ServerCommand command = httpCommandList.getCommand(query);
        outToClient.print("Server : " + command.execute(parameter));
        outToClient.flush();
    }
}
