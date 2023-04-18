package com.gabrielbazante.agendavotingapi.exceptions.notFoundExceptions;

public class AgendaNotFoundException extends RuntimeException {
    public AgendaNotFoundException(String message) {
        super(message);
    }
    public AgendaNotFoundException() {
        
    }
}
