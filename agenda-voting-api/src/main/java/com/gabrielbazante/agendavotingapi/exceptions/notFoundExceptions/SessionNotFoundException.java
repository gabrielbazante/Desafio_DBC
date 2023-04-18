package com.gabrielbazante.agendavotingapi.exceptions.notFoundExceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
    public SessionNotFoundException() {
        
    }
}
