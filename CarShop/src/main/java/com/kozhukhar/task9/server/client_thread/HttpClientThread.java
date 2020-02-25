package com.kozhukhar.task9.server.client_thread;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.task9.server.command.HttpCommandList;
import com.kozhukhar.task9.server.command.ServerCommand;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpClientThread implements Runnable {

    private Socket socket;

    private ServiceContext serviceContext;

    private static final Logger LOG = Logger.getLogger(TcpClientThread.class);

    public HttpClientThread(Socket socket, ServiceContext serviceContext) {
        this.socket = socket;
        this.serviceContext = serviceContext;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientSentence = inFromClient.readLine();

            StringTokenizer tokenizer = new StringTokenizer(clientSentence);
            String httpMethod = tokenizer.nextToken();
            String httpQuery = tokenizer.nextToken();

            DataOutputStream outClient = new DataOutputStream(socket.getOutputStream());
            if (httpMethod.equals(HttpRes.GET)) {
                if (httpQuery.equals(HttpRes.PAGE_COUNT)) {
                    countPage(outClient);
                } else if (httpQuery.startsWith(HttpRes.PAGE_ITEM)) {
                    getItemPage(outClient, httpQuery);
                } else {
                    sendResponse(404, "Page was not found", outClient);
                }
            }
        } catch (IOException ex) {
            LOG.info(Messages.IO_EXCEPTION + ex.getMessage());
        } catch (AppException ex) {
            LOG.info(Messages.COMMAND_COULD_NOT_BE_RECEIVED + ex.getMessage());
        } catch (Exception ex) {
            LOG.info(Messages.COMMAND_WAS_NOT_READ + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                LOG.info(Messages.IO_EXCEPTION + ex.getMessage());
            }
        }
    }

    private void sendResponse(int statusCode, String responseString, DataOutputStream outClient) throws Exception {
        String statusLine = "";
        String serverDetails = HttpRes.SERVER + ": Java Server";
        String contentLengthLine = "";
        String contentTypeLine = HttpRes.CONTENT_TYPE + ": text/html" + HttpRes.NEW_LINE;

        statusLine = getStatusCode(statusCode);

        statusLine += HttpRes.NEW_LINE;
        responseString = HttpRes.HTML_START + responseString + HttpRes.HTML_END;
        contentLengthLine = HttpRes.CONTENT_LENGTH + responseString.length() + HttpRes.NEW_LINE;

        outClient.writeBytes(statusLine);
        outClient.writeBytes(serverDetails);
        outClient.writeBytes(contentTypeLine);
        outClient.writeBytes(contentLengthLine);
        outClient.writeBytes(HttpRes.CONNECTION + ": close" + HttpRes.NEW_LINE);
        outClient.writeBytes(HttpRes.NEW_LINE);
        outClient.writeBytes(responseString);

        outClient.close();
    }

    private String getStatusCode(int statusCode) {
        String statusLine;
        if (statusCode == 200) {
            statusLine = HttpRes.HTTP_200;
        } else {
            statusLine = HttpRes.HTTP_404;
        }
        return statusLine;
    }

    private void countPage(DataOutputStream outClient) throws Exception {
        String responseBuffer = HttpRes.SERVER_MESSAGE;
        HttpCommandList httpCommandList = new HttpCommandList(serviceContext);
        ServerCommand command = httpCommandList.getCommand(HttpRes.COUNT_COMMAND);
        responseBuffer += command.execute(null);
        sendResponse(200, responseBuffer, outClient);
    }

    private void getItemPage(DataOutputStream outClient, String httpQuery) throws Exception {
        String responseBuffer = HttpRes.SERVER_MESSAGE;
        HttpCommandList httpCommandList = new HttpCommandList(serviceContext);
        ServerCommand command = httpCommandList.getCommand(HttpRes.GET_INFO_COMMAND);

        String parameter = getParameter(httpQuery);
        responseBuffer += command.execute(parameter);
        sendResponse(200, responseBuffer, outClient);
    }

    private String getParameter(String httpQuery) {
        String parameter = "";
        System.out.println(httpQuery);
        String[] queryParameters = httpQuery.split("=");
        if (queryParameters.length > 1) {
            parameter = queryParameters[1];
            parameter = parameter.replace("%20", " ");
        }
        return parameter;
    }
}
