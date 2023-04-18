package com.gabrielbazante.agendavotingapi.exceptions.notFoundExceptions;

public class VoterNotFoundException extends RuntimeException {
    public VoterNotFoundException(String message) {
        super(message);
    }
    public VoterNotFoundException() {
        
    }
}
