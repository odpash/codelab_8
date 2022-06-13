package org.olegpash.server.util;

import org.olegpash.common.exceptions.DatabaseException;
import org.olegpash.common.util.requests.LoginRequest;
import org.olegpash.common.util.requests.RegisterRequest;
import org.olegpash.common.util.responses.AuthResponse;
import org.olegpash.server.db.DBManager;

public class UsersManager {

    private final DBManager dbManager;

    public UsersManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public AuthResponse registerNewUser(RegisterRequest request) {
        try {
            if (!dbManager.checkUsersExistence(request.getUsername())) {
                dbManager.addUser(request.getUsername(), request.getPassword());
                return new AuthResponse(true, "Registration was completed successfully!");
            } else {
                return new AuthResponse(false, "This username already exists!");
            }
        } catch (DatabaseException e) {
            return new AuthResponse(false, e.getMessage());
        }
    }

    public AuthResponse loginUser(LoginRequest request) {
        try {
            boolean check = dbManager.validateUser(request.getUsername(), request.getPassword());
            if (check) {
                return new AuthResponse(true, "Login successful!");
            } else {
                return new AuthResponse(false, "Wrong login or password!");
            }
        } catch (DatabaseException e) {
            return new AuthResponse(false, e.getMessage());
        }
    }
}
