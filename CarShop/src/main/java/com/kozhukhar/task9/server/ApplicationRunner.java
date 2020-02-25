package com.kozhukhar.task9.server;

import com.kozhukhar.carshop.command.CarShopCommandFactory;
import com.kozhukhar.carshop.command.Command;
import com.kozhukhar.carshop.context.ServiceContext;
import com.kozhukhar.carshop.context.StorageContext;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop.util.LocaleMessageUtil;
import com.kozhukhar.carshop.view.ConsoleView;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static com.kozhukhar.carshop.ConsoleShopApp.initializingContext;

public class ApplicationRunner {

    private static final Logger LOG = Logger.getLogger(ApplicationRunner.class);

    public static void main(String[] args) throws AppException, IOException, ClassNotFoundException {
        ServiceContext serviceContext = initContextData();
        initServers(serviceContext);
        try {
            System.out.println(ConsoleView.MAIN_PAGE);
            CarShopCommandFactory carShopCommandFactory = new CarShopCommandFactory(serviceContext);
            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    String inputCommand = scanner.nextLine();
                    Command command = carShopCommandFactory.getCommand(inputCommand);
                    System.out.println(command.execute());
                } catch (AppException e) {
                    LOG.error(serviceContext.getLocaleMessageUtil().localizeMessage(
                            e.getMessage()));
                }
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }

    public static void initServers(ServiceContext serviceContext) {
        MyServer serverTcpObject = new MyServer(serviceContext, 3000, ServerType.TCP);
        MyServer serverHttpObject = new MyServer(serviceContext, 3001, ServerType.HTTP);

        Thread tcpServer = new Thread(serverTcpObject);
        Thread httpServer = new Thread(serverHttpObject);
        tcpServer.start();
        httpServer.start();
    }

    private static ServiceContext initContextData() throws AppException, IOException, ClassNotFoundException {
        LocaleMessageUtil localeMessageUtil = new LocaleMessageUtil();
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setLocaleMessageUtil(localeMessageUtil);
        StorageContext storageContext = new StorageContext();
        initializingContext(serviceContext, storageContext);
        return serviceContext;
    }

}
