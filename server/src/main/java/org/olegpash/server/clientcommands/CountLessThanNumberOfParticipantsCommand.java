package org.olegpash.server.clientcommands;


import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.CommandRequest;
import org.olegpash.common.util.responses.CommandResponse;
import org.olegpash.server.abstractions.AbstractClientCommand;
import org.olegpash.server.db.DBManager;
import org.olegpash.server.util.CollectionManager;

public class CountLessThanNumberOfParticipantsCommand extends AbstractClientCommand {

    private final DBManager dbManager;

    private final CollectionManager collectionManager;

    public CountLessThanNumberOfParticipantsCommand(DBManager dbManager, CollectionManager collectionManager) {
        super("count_less_than_number_of_participants",
                1,
                "print the number of groups whose number of participants is less than the specified one",
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
            int result = collectionManager.countLessThanNumberOfParticipants(request.getNumericArgument());
            return new CommandResponse(true, "Groups with fewer participants than "
                    + request.getNumericArgument()
                    + ": " + result);
        } catch (DatabaseException e) {
            return new CommandResponse(false, e.getMessage());
        }
    }
}
