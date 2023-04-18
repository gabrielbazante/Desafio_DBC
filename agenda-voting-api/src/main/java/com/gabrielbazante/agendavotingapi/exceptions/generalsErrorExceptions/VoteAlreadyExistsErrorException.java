package com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions;

public class VoteAlreadyExistsErrorException extends RuntimeException {
    public VoteAlreadyExistsErrorException(String message) {
        super(message);
    }
    public VoteAlreadyExistsErrorException() {
        
    }
    
}
