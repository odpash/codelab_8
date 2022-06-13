package org.olegpash.common.util.responses;


public class AuthResponse extends AbstractResponse {

    public AuthResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}