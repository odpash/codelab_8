package org.olegpash.server.clientcommands;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

import java.util.List;

public class ShowCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public ShowCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("show",
                0,
                "display all the elements of the collection and information about them");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            if (collectionManager.getMusicBands().isEmpty()) {
                return new CommandResponse(false, "Collection is empty");
            } else {
                List<Long> ids = dbManager.getIdsOfUsersElements(request.getUsername());
                return new CommandResponse(true, "Elements of collection:",
                        collectionManager.getUsersElements(ids),
                        collectionManager.getAlienElements(ids));
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
