package com.gabrielbazante.agendavotingapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.entity.Session;
import com.gabrielbazante.agendavotingapi.repository.SessionRepository;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private AgendaService agendaService;

    @InjectMocks
    private SessionService sessionService;


    @Test
    void testCreateSession() {
        Long idSession = 1L;
        Agenda agenda = new Agenda(idSession, "Agenda Test", "Test Description");
    
        when(agendaService.findById(idSession)).thenReturn(agenda);
    
        Session session = new Session(agenda);
        when(sessionRepository.save(any(Session.class))).thenReturn(session);
    
        Session createdSession = sessionService.createSession(idSession);
    
        assertEquals(session, createdSession);
    }
    
    
    

    @Test
    void testDisableById() {
        Long idSession = 1L;
        Session session = new Session(new Agenda(idSession, "Agenda Test", "Test Description"));
        when(sessionRepository.findByIdSession(idSession)).thenReturn(session);

        sessionService.disableById(idSession);

        assertEquals("DISABLED", session.getActive());
        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    void testIsSessionActive() {
        Long idAgenda = 1L;
        Agenda agenda = new Agenda(idAgenda, "Agenda Test", "Test Description");
        Session session = new Session(agenda);
        session.setActive("ENABLED");

        when(agendaService.findById(idAgenda)).thenReturn(agenda);
        when(sessionRepository.findSessionByIdAgenda(agenda)).thenReturn(session);

        boolean isActive = sessionService.isSessionActive(idAgenda);
        assertTrue(isActive);
        session.setActive("DISABLED");
        isActive = sessionService.isSessionActive(idAgenda);

        assertFalse(isActive);
    }

}

