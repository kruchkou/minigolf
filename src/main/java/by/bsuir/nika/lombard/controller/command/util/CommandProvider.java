package by.bsuir.nika.lombard.controller.command.util;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.controller.command.impl.*;
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
        commands.put(CommandName.GO_TO_ALL_CLIENTS_COMMAND, new GoToAllClientsCommand());
        commands.put(CommandName.GO_TO_CLIENT_COMMAND, new GoToClientCommand());
        commands.put(CommandName.GO_TO_CREATE_CLIENT_COMMAND, new GoToCreateClientCommand());
        commands.put(CommandName.GO_TO_UPDATE_CLIENT_COMMAND, new GoToUpdateClientCommand());
        commands.put(CommandName.CREATE_CLIENT_COMMAND, new CreateClientCommand());
        commands.put(CommandName.UPDATE_CLIENT_COMMAND, new UpdateClientCommand());
        commands.put(CommandName.DELETE_CLIENT_COMMAND, new DeleteClientCommand());
        commands.put(CommandName.GO_TO_ALL_ITEMS_COMMAND, new GoToAllItemsCommand());
        commands.put(CommandName.GO_TO_ITEM_COMMAND, new GoToItemCommand());
        commands.put(CommandName.GO_TO_CREATE_ITEM_COMMAND, new GoToCreateItemCommand());
        commands.put(CommandName.GO_TO_UPDATE_ITEM_COMMAND, new GoToUpdateItemCommand());
        commands.put(CommandName.CREATE_ITEM_COMMAND, new CreateItemCommand());
        commands.put(CommandName.UPDATE_ITEM_COMMAND, new UpdateItemCommand());
        commands.put(CommandName.DELETE_ITEM_COMMAND, new DeleteItemCommand());
        commands.put(CommandName.REALISE_ITEM_COMMAND, new RealiseItemCommand());
        commands.put(CommandName.GO_TO_REPORT_COMMAND, new GoToReportCommand());
    }

    //Метод, возвращающий команду по ее имени
    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        CommandName enumName = CommandName.valueOf(commandName);

        return commands.get(enumName);
    }
}
