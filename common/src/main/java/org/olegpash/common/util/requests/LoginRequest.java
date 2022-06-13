package org.olegpash.common.util.requests;

public class LoginRequest extends AbstractRequest {

    private final String username;

    private final String password;

    public LoginRequest(String username, String password, String clientInfo) {
        super(clientInfo);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Login request";
    }
}