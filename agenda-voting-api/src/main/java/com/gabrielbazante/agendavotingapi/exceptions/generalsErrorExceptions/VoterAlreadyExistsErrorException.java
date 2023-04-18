package com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions;

public class VoterAlreadyExistsErrorException extends RuntimeException {
    public VoterAlreadyExistsErrorException(String message) {
        super(message);
    }
    public VoterAlreadyExistsErrorException() {
        
    }
    
}
