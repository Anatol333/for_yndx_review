package com.kozhukhar.task9.client;

import com.kozhukhar.carshop_online.exception.Messages;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {

    private static final String EXIT_COMMAND = "exit";
    private static final int PORT = 3000;
    private static final String HOST_NAME = "localhost";
    private static final Logger LOG = Logger.getLogger(TcpClient.class);

    public static void main(String[] args) {
        String command = "";
        Scanner scanner = new Scanner(System.in);

        while (!command.equals(EXIT_COMMAND)) {
            try (Socket socket = new Socket(HOST_NAME, PORT)) {
                System.out.println("Input command: ");

                command = scanner.nextLine();
                PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                outToServer.print(command + System.lineSeparator());
                outToServer.flush();

                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String serverOutput = inFromServer.readLine();
                System.out.println(serverOutput);
            } catch (UnknownHostException ex) {
                LOG.info(Messages.SERVER_WAS_NOT_FOUND + ex.getMessage());
            } catch (IOException ex) {
                LOG.info(Messages.IO_EXCEPTION + ex.getMessage());
            }
        }

    }
}
