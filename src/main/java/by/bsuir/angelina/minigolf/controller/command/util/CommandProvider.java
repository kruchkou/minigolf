package by.bsuir.angelina.minigolf.controller.command.util;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.controller.command.impl.CreateOrderCommand;
import by.bsuir.angelina.minigolf.controller.command.impl.DeleteOrderCommand;
import by.bsuir.angelina.minigolf.controller.command.impl.UpdateOrderCommand;
import by.bsuir.angelina.minigolf.controller.command.impl.go.GoToAllOrdersCommand;
import by.bsuir.angelina.minigolf.controller.command.impl.go.GoToCreateOrderCommand;
import by.bsuir.angelina.minigolf.controller.command.impl.go.GoToOrderCommand;
import by.bsuir.angelina.minigolf.controller.command.impl.go.GoToUpdateOrderCommand;
import by.bsuir.nika.lombard.controller.command.impl.go.*;
import lombok.Getter;

import java.util.HashMap;

public class CommandProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static CommandProvider instance = new CommandProvider();

    //HashMap, хранящий экземпляры всех комманд
    private final HashMap<CommandName, Command> commands = new HashMap<>();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CommandProvider() {
        commands.put(CommandName.GO_TO_ORDER_COMMAND, new GoToOrderCommand());
        commands.put(CommandName.GO_TO_ALL_ORDERS_COMMAND, new GoToAllOrdersCommand());
        commands.put(CommandName.GO_TO_CREATE_ORDER_COMMAND, new GoToCreateOrderCommand());
        commands.put(CommandName.GO_TO_UPDATE_ORDER_COMMAND, new GoToUpdateOrderCommand());
        commands.put(CommandName.CREATE_ORDER_COMMAND, new CreateOrderCommand());
        commands.put(CommandName.UPDATE_ORDER_COMMAND, new UpdateOrderCommand());
        commands.put(CommandName.DELETE_ORDER_COMMAND, new DeleteOrderCommand());
    }

    //Метод, возвращающий команду по ее имени
    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        CommandName enumName = CommandName.valueOf(commandName);

        return commands.get(enumName);
    }
}
