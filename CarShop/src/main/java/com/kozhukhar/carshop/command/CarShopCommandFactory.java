package com.kozhukhar.carshop.command;

import com.kozhukhar.carshop.command.cart.AddProductToCartCommand;
import com.kozhukhar.carshop.command.cart.OrderProductsFromCart;
import com.kozhukhar.carshop.command.cart.ShowCartInfoCommand;
import com.kozhukhar.carshop.context.ServiceContext;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CarShopCommandFactory {

    private Map<String, Command> actions = new HashMap<>();

    private Command command;

    private String secondCommand;

    private ServiceContext serviceContext;

    private static final Logger LOG = Logger.getLogger(CarShopCommandFactory.class);

    public CarShopCommandFactory() {

    }

    public CarShopCommandFactory(ServiceContext serviceContext) {
        this.serviceContext = serviceContext;
        initialisingCommands();
    }

    private void initialisingCommands() {
        actions.put("--home", new ShowHomePageCommand());
        actions.put("--exit", new ExitFromApplicationCommand(serviceContext.getCatalogService(), secondCommand));
        actions.put("--show", new ShowCatalogCommand(serviceContext.getCatalogService()));
        actions.put("--add", new AddProductToCartCommand(secondCommand, serviceContext.getCartService()));
        actions.put("--sinfo", new ShowLastFiveCommand(serviceContext.getCartService()));
        actions.put("--cart", new ShowCartInfoCommand(serviceContext.getCartService()));
        actions.put("--order", new OrderProductsFromCart(serviceContext.getCartService()));
        actions.put("--history", new ShowAllPurchaseHistory(secondCommand, serviceContext.getCartService()));
        actions.put("--addNew", new AddNewToCatalogCommand(serviceContext.getCatalogService(), secondCommand, serviceContext.getCreatorAnnotation()));
        actions.put("--addRef", new AddNewToCatalogReflection(serviceContext.getCatalogService(), serviceContext.getCreatorAnnotation()));
        actions.put("--locale", new ChangeLocaleCommand(serviceContext, secondCommand));
        actions.put("noFound", new ShowNoFoundPageCommand());
    }

    /**
     * Returns action object with the given name.
     *
     * @param commandName Name of incoming command
     * @return Command object.
     */
    public Command getCommand(String commandName) {
        LOG.debug("Command name auth : " + commandName);
        command = containsInKey(commandName);
        if (command == null) {
            return actions.get("noFound");
        }
        return command;
    }

    private Command containsInKey(String commandName) {
        secondCommand = null;
        String[] commandStack = commandName.split(" ");
        if (commandStack.length > 1) {
            StringBuilder otherCommand = new StringBuilder();
            for (int i = 1; i < commandStack.length; ++i) {
                otherCommand.append(commandStack[i]).append(" ");
            }
            secondCommand = otherCommand.substring(0, otherCommand.length() - 1);
        }
        initialisingCommands();
        return actions.get(commandStack[0]);
    }
}
