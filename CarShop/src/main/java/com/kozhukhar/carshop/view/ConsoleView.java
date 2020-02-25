package com.kozhukhar.carshop.view;

public class ConsoleView {

    public static final String MAIN_PAGE = "\n--show\t\t\t\t\t-\tshow car-catalog;\n" +
            "--add [car_name]\t\t-\tadd car to the cart\n" +
            "--sinfo\t\t\t\t\t-\tshow info about last five products.\n" +
            "--cart\t\t\t\t\t-\tshow cart contents;\n" +
            "--order\t\t\t\t\t-\tbuy all products from the cart;\n" +
            "--history [DATE-START]-[DATE-END]\t-\tshow all purchase history (date format is [yyyy/MM/dd 00:00]);\n" +
            "--addNew\t-\tadd new transport\n" +
            "--exit\t\t\t\t\t-\texit from application;\n";

    public static final String ERROR_404 = "\n========================================================\n" +
            "\t\t\t\t\tERROR 404\n" +
            "\t\t\t\tPage was not found" +
            "\n========================================================\n";

    public static final String ADD_INFO = "----------------INFO------------------------------\n" +
            "     1 - Transport;\n" +
            "     2 - Car;\n" +
            "     3 - Truck;\n" +
            "     4 - Train;\n" +
            "   --addNew   <-- Auto-Generating transport\n" +
            "   --addNew [number] [name] [model] [price]\n" +
            "--------------------------------------------------";

}
