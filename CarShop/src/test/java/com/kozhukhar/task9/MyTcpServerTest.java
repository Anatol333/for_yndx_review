package com.kozhukhar.task9;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.task9.server.MyServer;
import com.kozhukhar.task9.server.ServerType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyTcpServerTest {

    @Mock
    private ServiceContext serviceContext;

    @Mock
    private CatalogService catalogService;

    @Mock
    private CatalogStore catalogStore;

    private Thread server;

    private int port = 3001;
    private static final String TEST_NAME = "test name";
    private static final String GET_COUNT_COMMAND = "get_count";
    private static final String SERVER_HOST = "localhost";
    private static final String TEST_PRODUCT = "product | price";
    private static final String GET_ITEM_COMMAND = "get_item=" + TEST_NAME;

    @Before
    public void setUp() throws IOException {
        port = SocketHolder.findFreePort();
        when(serviceContext.getCatalogService()).thenReturn(catalogService);
        when(catalogService.getCatalogStore()).thenReturn(catalogStore);

        MyServer serverObject = new MyServer(serviceContext, port, ServerType.TCP);
        server = new Thread(serverObject);
        server.start();
    }

    @After
    public void tearDown() {
        server.interrupt();
    }

    @Test
    public void testClientThreadCommandCount() throws IOException {
        Socket socket = new Socket(SERVER_HOST, port);
        when(catalogStore.getFullProductInfo()).thenReturn(new HashMap<>());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter outToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        outToServer.write(GET_COUNT_COMMAND + System.lineSeparator());
        outToServer.flush();

        assertEquals("Server : 0", inFromServer.readLine());
    }

    @Test
    public void testGetItemRequestToServer() throws IOException, AppException {
        Socket socket = new Socket(SERVER_HOST, port);
        when(catalogService.getProductByName(TEST_NAME)).thenReturn(TEST_PRODUCT);

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter outToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        outToServer.write(GET_ITEM_COMMAND + System.lineSeparator());
        outToServer.flush();

        assertEquals("Server : " + TEST_PRODUCT, inFromServer.readLine());
        socket.close();
    }

}
