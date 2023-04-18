package com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions;

public class AgendaAlreadyExistsErrorException extends RuntimeException {
    public AgendaAlreadyExistsErrorException(String message) {
        super(message);
    }
    public AgendaAlreadyExistsErrorException() {
        
    }
    
}
