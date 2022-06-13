package org.olegpash.server.clientcommands;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class InfoCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public InfoCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("info",
                0,
                "display information about the collection");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            return new CommandResponse(true, "Info about collection:\n" + collectionManager.returnInfo());
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
