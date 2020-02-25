package com.kozhukhar.task9;

import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.service.CatalogService;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.task9.server.MyServer;
import com.kozhukhar.task9.server.ServerType;
import org.json.simple.JSONObject;
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
public class MyHttpServerTest {

    @Mock
    private ServiceContext serviceContext;

    @Mock
    private CatalogService catalogService;

    @Mock
    private CatalogStore catalogStore;

    private Thread server;

    private int port = 3005;
    private static final String TEST_NAME = "test%20name";
    private static final String GET_COUNT_COMMAND = "GET /shop/count HTTP/1.1";
    private static final String GET_ITEM_COMMAND = "GET /shop/item?get_info=" + TEST_NAME + " HTTP/1.1";
    private static final String SERVER_HOST = "localhost";
    private static final String SUCCESSFUL = "HTTP/1.1 200 OK";

    @Before
    public void setUp() throws IOException {
        port = SocketHolder.findFreePort();
        when(serviceContext.getCatalogService()).thenReturn(catalogService);
        when(catalogService.getCatalogStore()).thenReturn(catalogStore);

        MyServer serverObject = new MyServer(serviceContext, port, ServerType.HTTP);
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

        assertEquals(SUCCESSFUL, inFromServer.readLine());
    }

    @Test
    public void testGetItemRequestToServer() throws IOException, AppException {
        Socket socket = new Socket(SERVER_HOST, port);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("price", 0);

        String nameProduct = TEST_NAME.replace("%20", " ");
        when(catalogService.getJsonProductByName(nameProduct)).thenReturn(jsonObject);

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter outToServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        outToServer.write(GET_ITEM_COMMAND + System.lineSeparator());
        outToServer.flush();

        assertEquals(SUCCESSFUL, inFromServer.readLine());
    }

}
