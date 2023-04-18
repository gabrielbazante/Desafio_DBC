package com.gabrielbazante.agendavotingapi.exceptions.updateErrorExceptions;

public class VoterUpdateErrorException extends RuntimeException {
    public VoterUpdateErrorException(String message) {
        super(message);
    }
    public VoterUpdateErrorException() {
        
    }
}
