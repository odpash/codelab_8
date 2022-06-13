package org.olegpash.common.util.responses;


import org.olegpash.common.abstractions.AbstractResponse;

public class AuthResponse extends AbstractResponse {

    public AuthResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}