package com.gabrielbazante.agendavotingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbazante.agendavotingapi.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
    
    Agenda findByIdAgenda(Long idAgenda);

    Agenda findByTitle (String title);

    void deleteById (Long id);
}
