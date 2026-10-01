package com.msb.embeddedbanking.exception;
import static com.msb.embeddedbanking.exception.ResponseEnum.INVALID_CREDENTIAL;
public class InvalidCredentialException extends BaseCustomException {

    public InvalidCredentialException() {
        super(INVALID_CREDENTIAL.getMessage());
    }
}