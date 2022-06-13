package org.olegpash.server.clientcommands;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class UpdateCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public UpdateCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("update", 1,
                "update the value of a collection item whose id is equal to the specified one",
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
            if (dbManager.updateById(request.getBandArgument(), request.getNumericArgument(), request.getUsername())) {
                collectionManager.updateById(request.getNumericArgument(), request.getBandArgument());
                return new CommandResponse(true, "Element with ID " + request.getNumericArgument()
                        + " was successfully updated");
            } else {
                return new CommandResponse(false, "Element was created by another user, you don't "
                        + "have permission to update it");
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
