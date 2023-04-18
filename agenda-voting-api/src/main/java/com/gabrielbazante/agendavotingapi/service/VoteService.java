package com.gabrielbazante.agendavotingapi.service;

import java.time.LocalDateTime;

import org.apache.tomcat.util.bcel.classfile.JavaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbazante.agendavotingapi.DTO.VoteDTO;
import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.entity.Session;
import com.gabrielbazante.agendavotingapi.entity.Vote;
import com.gabrielbazante.agendavotingapi.entity.Voter;
import com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions.SessionDisabledErrorException;
import com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions.VoteAlreadyExistsErrorException;
import com.gabrielbazante.agendavotingapi.repository.SessionRepository;
import com.gabrielbazante.agendavotingapi.repository.VoteRepository;

@Service
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;
    private VoterService voterService;
    private AgendaService agendaService;
    private SessionService sessionService;
    private SessionRepository sessionRepository;

    private final Logger logger = LoggerFactory.getLogger(JavaClass.class);

    public VoteService(VoteRepository voteRepository, VoterService voterService, AgendaService agendaService, SessionService sessionService, SessionRepository sessionRepository) {
        this.voteRepository = voteRepository;
        this.voterService = voterService;
        this.agendaService = agendaService;
        this.sessionService = sessionService;
        this.sessionRepository = sessionRepository;
    }


    public Boolean isVoteInAgenda(VoteDTO voteDTO) {
        logger.info("Checking if vote is in agenda");
        Voter voter = voterService.findByCpf(voteDTO.getCpf());
        return voteRepository.findVoteByIdAgenda(voteDTO.getIdAgenda(), voter.getIdVoter()) != null;
    }

    public int countTotalVotesPerAgendaYes(Long idAgenda) {
        logger.info("Counting total votes - Yes - per agenda");
        return voteRepository.countTotalVotesPerAgendaYes(idAgenda);
    }

    public int countTotalVotesPerAgendaNo(Long idAgenda) {
        logger.info("Counting total votes - No - per agenda");
        return voteRepository.countTotalVotesPerAgendaNo(idAgenda);
    }

    public Vote createVote(VoteDTO voteDTO) {
        logger.info("Creating vote");
        if(sessionService.isSessionActive(voteDTO.getIdAgenda()) == false) {
            logger.info("Session disabled");
            throw new SessionDisabledErrorException("Session disabled");
        }else {
            if(isVoteInAgenda(voteDTO) == true) {
                logger.info("Vote already exists");
                throw new VoteAlreadyExistsErrorException("Vote already exists");
            }
            Voter voter = voterService.findByCpf(voteDTO.getCpf());
            Agenda agenda = agendaService.findById(voteDTO.getIdAgenda());
            Session session = sessionRepository.findSessionByIdAgenda(agenda);

            Vote vote = new Vote(voter, agenda, voteDTO.getVote());
            
            try {
                logger.info("Saving vote");
                LocalDateTime endSessionTime = session.getStartingVote().plusMinutes(10);

                if(LocalDateTime.now().isAfter(endSessionTime)) {
                    sessionService.disabledSession(session.getIdSession());
                    throw new SessionDisabledErrorException("You can't vote, becausa the session is disabled");
                }
                return voteRepository.save(vote);
    
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}
