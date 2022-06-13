package org.olegpash.server.clientcommands;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

import java.util.List;

public class RemoveAnyByNumberOfParticipantsCommand extends AbstractClientCommand {

    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public RemoveAnyByNumberOfParticipantsCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("remove_any_by_number_of_participants",
                1,
                "delete a group with a specified number of members from the collection",
                "number of participants");
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public CommandResponse executeClientCommand(CommandRequest request) {
        try {
            if (!dbManager.validateUser(request.getUsername(), request.getPassword())) {
                return new CommandResponse(false, "Login and password mismatch");
            }
            List<Long> ids = collectionManager.returnIDbyNumberOFParticipants(request.getNumericArgument());
            if (ids.isEmpty()) {
                return new CommandResponse(false, "There is no band with number of participants equals "
                        + request.getNumericArgument());
            } else {
                for (Long id : ids) {
                    if (dbManager.removeById(id, request.getUsername())) {
                        collectionManager.removeBandById(id);
                        return new CommandResponse(true, "MusicBand with " + request.getNumericArgument()
                                + " participants and ID equals "
                                + id + " was removed");
                    }
                }
                return new CommandResponse(false, "There are no such elements, that belong to you "
                        + "in this collection with " + request.getNumericArgument() + " participants");
            }
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
