package com.gabrielbazante.agendavotingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
    
    Session findByIdSession(Long idSession);

    Session findSessionByIdAgenda (Agenda agenda);

}
