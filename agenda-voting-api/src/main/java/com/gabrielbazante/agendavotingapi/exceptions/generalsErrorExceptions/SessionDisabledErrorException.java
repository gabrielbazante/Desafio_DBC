package com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions;

public class SessionDisabledErrorException extends RuntimeException {
    public SessionDisabledErrorException(String message) {
        super(message);
    }
    public SessionDisabledErrorException() {
        
    }
    
}
