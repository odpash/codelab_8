package org.olegpash.server.clientcommands;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class RemoveByIdCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("remove_by_id",
                1,
                "delete a group from a collection by its id",
                "id");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            if (!dbManager.checkBandExistence(request.getNumericArgument())) {
                return new CommandResponse(false, "There is no element with such ID");
            }
            if (dbManager.removeById(request.getNumericArgument(), request.getUsername())) {
                collectionManager.removeBandById(request.getNumericArgument());
                return new CommandResponse(true, "Element with ID " + request.getNumericArgument()
                        + " was removed from the collection");
            } else {
                return new CommandResponse(false, "Element was created by another user, you don't "
                        + "have permission to remove it");
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
