package com.gabrielbazante.agendavotingapi.service;

import org.apache.tomcat.util.bcel.classfile.JavaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbazante.agendavotingapi.entity.Voter;
import com.gabrielbazante.agendavotingapi.exceptions.createErrorExceptions.VoterCreateErrorException;
import com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions.VoterAlreadyExistsErrorException;
import com.gabrielbazante.agendavotingapi.exceptions.notFoundExceptions.VoterNotFoundException;
import com.gabrielbazante.agendavotingapi.exceptions.updateErrorExceptions.VoterUpdateErrorException;
import com.gabrielbazante.agendavotingapi.repository.VoterRepository;

@Service
public class VoterService {
    private final Logger logger = LoggerFactory.getLogger(JavaClass.class);
    
    @Autowired
    private VoterRepository voterRepository;

    public Voter findById(Long id) {
        logger.info("Finding voter by id");
        return voterRepository.findByIdVoter(id);
    }

    public Voter findByIdVoter(Long id) {
        logger.info("Finding voter by id");
        return voterRepository.findByIdVoter(id);
    }

    public Voter findByCpf(String cpf) {
        logger.info("Finding voter by cpf");
        return voterRepository.findByCpf(cpf);
    }

    public Voter createVoter(Voter voter) {
        try {
            logger.info("Creating voter");
            if (voterRepository.findByCpf(voter.getCpf()) != null) {
                logger.info("Voter already exists");
                throw new VoterAlreadyExistsErrorException("Voter already exists");
            }
            return voterRepository.save(voter);
        } catch (Exception e) {
            throw new VoterCreateErrorException("Error creating voter");
        }
    }

    public Voter updateVoter(Long id, Voter voter) {
        try {
            logger.info("Updating voter");
            if (voterRepository.findByCpf(voter.getCpf()) == null) {
                logger.info("Voter not found");
                throw new VoterNotFoundException("Voter not found");
            }

            Voter currentVoter = voterRepository.findByIdVoter(id);
            currentVoter.setCpf(voter.getCpf());
            currentVoter.setName(voter.getName());

            return voterRepository.save(currentVoter);
        } catch (Exception e) {
            throw new VoterUpdateErrorException("Error updating voter");
        }
    }

    public void deleteById(Long id) {
        logger.info("Deleting voter by id");
        voterRepository.deleteById(id);
    }
    
}
