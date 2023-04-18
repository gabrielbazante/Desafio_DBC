package com.gabrielbazante.agendavotingapi.exceptions.notFoundExceptions;

public class VoteNotFoundException extends RuntimeException {
    public VoteNotFoundException(String message) {
        super(message);
    }
    public VoteNotFoundException() {
        
    }
}
