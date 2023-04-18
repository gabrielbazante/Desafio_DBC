package com.gabrielbazante.agendavotingapi.service;

import org.apache.tomcat.util.bcel.classfile.JavaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.entity.Session;
import com.gabrielbazante.agendavotingapi.repository.SessionRepository;

@Service
public class SessionService {
    
    private final SessionRepository sessionRepository;
    private final AgendaService agendaService;

    private final Logger logger = LoggerFactory.getLogger(JavaClass.class);

    public SessionService(SessionRepository sessionRepository, AgendaService agendaService) {
        this.sessionRepository = sessionRepository;
        this.agendaService = agendaService;
    }

    public Session createSession(Long idSession) {
        logger.info("Creating session");
        Agenda agenda = agendaService.findById(idSession);
        Session session = new Session(agenda);
        return sessionRepository.save(session);
    }
    
    public void disableById (Long id){
        logger.info("Disabling session by id");
        Session session = sessionRepository.findByIdSession(id);
        session.setActive("DISABLED");
        sessionRepository.save(session);
    }

    public Session disabledSession(Long idSession) {
        logger.info("Disabling session");
        Session session = sessionRepository.findByIdSession(idSession);
        session.setActive("DISABLED");
        return sessionRepository.save(session);
    }

    public boolean isSessionActive(Long idAgenda) {
        logger.info("Checking if session is active");
        Agenda agenda = agendaService.findById(idAgenda);
        Session session = sessionRepository.findSessionByIdAgenda(agenda);
        if((session.getActive().equals("DISABLED")) ) {
            return false;
        }
        return true;
    }

}
