package org.olegpash.server.clientcommands;


import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

import java.util.List;

public class ClearCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public ClearCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("clear",
                0,
                "clear the collection");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            List<Long> deletedIDs = dbManager.clear(request.getUsername());
            if (deletedIDs.isEmpty()) {
                return new CommandResponse(false, "You don't have elements in this collection!");
            } else {
                deletedIDs.forEach(collectionManager::removeBandById);
                return new CommandResponse(true, "Your elements were removed from the collection, their IDs:", deletedIDs);
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
