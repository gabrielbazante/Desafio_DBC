package com.gabrielbazante.agendavotingapi.controller;

import com.gabrielbazante.agendavotingapi.DTO.SessionDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbazante.agendavotingapi.entity.Session;
import com.gabrielbazante.agendavotingapi.service.SessionService;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    
    @PostMapping("/session")
    public Session session(@RequestBody SessionDTO sessionDTO) {
        return sessionService.createSession(sessionDTO.getIdAgenda());
    }

    @DeleteMapping("/session/{id}")
    public void disableSession(@PathVariable Long id) {
        sessionService.disableById(id);
    }

}
