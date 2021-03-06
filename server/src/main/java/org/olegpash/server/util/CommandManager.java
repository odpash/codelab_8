package org.olegpash.server.util;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.TextColoring;
import org.olegpash.common.util.requests.CheckIdRequest;
import org.olegpash.common.util.requests.CollectionRequest;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CheckIdResponse;
import org.olegpash.common.util.responses.CollectionResponse;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.ServerConfig;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.abstractions.AbstractServerCommand;
import org.olegpash.server.clientcommands.*;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.servercommands.ServerExitCommand;
import org.olegpash.server.servercommands.ServerHelpCommand;
import org.olegpash.server.servercommands.ServerHistoryCommand;

import java.time.format.DateTimeFormatter;

public class CommandManager {
    private final DBManager dbManager;

    private final CollectionManager collectionManager;

    public CommandManager(DBManager dbManager, CollectionManager collectionManager) {
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
        setCommands();
    }

    private void setCommands() {
        AbstractClientCommand infoCommand = new InfoCommand(dbManager, collectionManager);
        AbstractClientCommand showCommand = new ShowCommand(dbManager, collectionManager);
        AbstractClientCommand addCommand = new AddCommand(dbManager, collectionManager);
        AbstractClientCommand updateCommand = new UpdateCommand(dbManager, collectionManager);
        AbstractClientCommand removeByIdCommand = new RemoveByIdCommand(dbManager, collectionManager);
        AbstractClientCommand clearCommand = new ClearCommand(dbManager, collectionManager);
        AbstractClientCommand addIfMaxCommand = new AddIfMinCommand(dbManager, collectionManager);
        AbstractClientCommand removeGreaterCommand = new RemoveGreaterCommand(dbManager, collectionManager);
        AbstractClientCommand historyCommand = new HistoryCommand(ServerConfig.getClientCommandHistory().getHistory(), dbManager);
        AbstractClientCommand countByGenreCommand = new CountByGenreCommand(dbManager, collectionManager);
        AbstractServerCommand helpServerCommand = new ServerHelpCommand(ServerConfig.getServerAvailableCommands());
        AbstractServerCommand exitServerCommand = new ServerExitCommand();
        AbstractServerCommand historyServerCommand = new ServerHistoryCommand(ServerConfig.getClientCommandHistory().getHistory());

        ServerConfig.getClientAvailableCommands().put(infoCommand.getName(), infoCommand);
        ServerConfig.getClientAvailableCommands().put(showCommand.getName(), showCommand);
        ServerConfig.getClientAvailableCommands().put(addCommand.getName(), addCommand);
        ServerConfig.getClientAvailableCommands().put(updateCommand.getName(), updateCommand);
        ServerConfig.getClientAvailableCommands().put(removeByIdCommand.getName(), removeByIdCommand);
        ServerConfig.getClientAvailableCommands().put(clearCommand.getName(), clearCommand);
        ServerConfig.getClientAvailableCommands().put(addIfMaxCommand.getName(), addIfMaxCommand);
        ServerConfig.getClientAvailableCommands().put(removeGreaterCommand.getName(), removeGreaterCommand);
        ServerConfig.getClientAvailableCommands().put(historyCommand.getName(), historyCommand);
        ServerConfig.getClientAvailableCommands().put(countByGenreCommand.getName(), countByGenreCommand);
        ServerConfig.getServerAvailableCommands().put(helpServerCommand.getName(), helpServerCommand);
        ServerConfig.getServerAvailableCommands().put(exitServerCommand.getName(), exitServerCommand);
        ServerConfig.getServerAvailableCommands().put(historyServerCommand.getName(), historyServerCommand);
    }

    public CommandResponse executeClientCommand(CommandRequest request) {
        ServerConfig.getClientCommandHistory().pushCommand(request.getCurrentTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                + " " + request.getClientInfo() + ": " + request.getCommandName());
        return ServerConfig.getClientAvailableCommands().get(request.getCommandName()).executeClientCommand(request);
    }

    public String executeServerCommand(String commandName) {
        if (ServerConfig.getServerAvailableCommands().containsKey(commandName)) {
            return ServerConfig.getServerAvailableCommands().get(commandName).executeServerCommand();
        } else {
            return TextColoring.getRedText("There is no such command, type HELP to get list on commands");
        }
    }

    public CheckIdResponse checkId(CheckIdRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CheckIdResponse(false, "Login and password mismatch");
            }
            if (dbManager.checkBandExistence(request.getId())) {
                return new CheckIdResponse(true, "Band with this ID exists");
            } else {
                return new CheckIdResponse(false, "Band with this ID doesn't exist");
            }
        } catch (DatabaseException e) {
            return new CheckIdResponse(false, e.getMessage());
        }
    }

    public CollectionResponse returnCollection(CollectionRequest commandRequest) {
        try {
            if (!dbManager.validateUser(commandRequest.getUsername(), commandRequest.getPassword())) {
                return new CollectionResponse(false, "Login and password mismatch", null, null);
            }
            return new CollectionResponse(true, "Ok", collectionManager.getMusicBands(), dbManager.getIdsOfUsersElements(commandRequest.getUsername()));
        } catch (DatabaseException e) {
            return new CollectionResponse(false, e.getMessage(), null, null);
        }
    }
}
