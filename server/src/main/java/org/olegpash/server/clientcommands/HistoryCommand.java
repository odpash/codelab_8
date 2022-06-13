package org.olegpash.server.clientcommands;


import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;

import java.util.ArrayDeque;

public class HistoryCommand extends AbstractClientCommand {

    private final ArrayDeque<String> queueOfCommands;
    private final DBManager dbManager;

    public HistoryCommand(ArrayDeque<String> queueOfCommands, DBManager dbManager) {
        super("history",
                0,
                "output the last 9 commands");
        this.queueOfCommands = queueOfCommands;
        this.dbManager = dbManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            StringBuilder sb = new StringBuilder();
            if (!queueOfCommands.isEmpty()) {
                for (String name : queueOfCommands) {
                    sb.append(name).append("\n");
                }
            } else {
                sb.append("History is empty");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            return new CommandResponse(true, sb.toString());
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
