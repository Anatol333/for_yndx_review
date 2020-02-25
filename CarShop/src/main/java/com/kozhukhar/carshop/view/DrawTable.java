package com.kozhukhar.carshop.view;

import com.kozhukhar.carshop.entity.Transport;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop.entity.bean.OrderBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DrawTable {

    public static String drawProductTable(List<Transport> transports) {
        String leftAlignFormat = "| %-24s | %-18d |%n";

        StringBuilder table = new StringBuilder();
        table.append(System.lineSeparator());
        table.append(String.format("+--------------------------+--------------------+%n"));
        table.append(String.format("|                    C A T A L O G              |%n"));
        table.append(String.format("+--------------------------+--------------------+%n"));
        table.append(String.format("| Product Name             |     Price          |%n"));
        table.append(String.format("+--------------------------+--------------------+%n"));
        for (int i = 0; i < transports.size(); i++) {
            table.append(String.format(leftAlignFormat,
                    transports.get(i).getName() + " " + transports.get(i).getModel(),
                    transports.get(i).getPrice()));
        }
        table.append(String.format("+--------------------------+--------------------+%n"));
        return table.toString();
    }

    public static String drawCartTable(List<CartEntry> orderHistory) {
        String leftAlignFormat = "| %-24s | %-18d |%n";

        StringBuilder table = new StringBuilder();
        table.append(System.lineSeparator());
        table.append(String.format("+--------------------------+--------------------+%n"));
        table.append(String.format("|                      C A R T                  |%n"));
        table.append(String.format("+--------------------------+--------------------+%n"));
        table.append(String.format("| Product Name             | Count in the cart  |%n"));
        table.append(String.format("+--------------------------+--------------------+%n"));
        for (int i = 0; i < orderHistory.size(); i++) {
            table.append(String.format(leftAlignFormat,
                    orderHistory.get(i).getObjectOfProduct().toString(),
                    orderHistory.get(i).getCountOfProduct()));
        }
        table.append(String.format("+--------------------------+--------------------+%n"));
        return table.toString();
    }

    public static String drawHistoryTable(TreeMap<LocalDateTime, OrderBean> orderHistory) {
        String leftAlignFormat = "| %-30s | %-50s | %-18s |%n";

        StringBuilder table = new StringBuilder();
        table.append(System.lineSeparator());
        table.append(String.format("+--------------------------------+----------------------------------------------------+--------------------+%n"));
        table.append(String.format("|                                O R D E R I N G  H I S T O R Y                                            |%n"));
        table.append(String.format("+--------------------------------+----------------------------------------------------+--------------------+%n"));
        table.append(String.format("| Ordering date                  | Product/s                                          |    Total Price     |%n"));
        table.append(String.format("+--------------------------------+----------------------------------------------------+--------------------+%n"));
        for (Map.Entry<LocalDateTime, OrderBean> entry : orderHistory.entrySet()) {
            List<CartEntry> cartEntries = entry.getValue().getCartEntry();
            StringBuilder productsOutput = new StringBuilder();
            cartEntries.forEach(element -> productsOutput.append(
                    element.getObjectOfProduct()).append("; ")
            );
            table.append(String.format(leftAlignFormat,
                    entry.getKey(),
                    productsOutput,
                    entry.getValue().getFullOrderPrice()
            ));
        }
        table.append(String.format("+--------------------------------+----------------------------------------------------+--------------------+%n"));
        return table.toString();
    }

    public static String drawContentReflection(List<Transport> transports) {
        StringBuilder output = new StringBuilder();
        transports.forEach(obj -> output.append(obj).append(System.lineSeparator()));
        return output.toString();
    }
}
