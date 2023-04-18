package com.gabrielbazante.agendavotingapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.exceptions.createErrorExceptions.AgendaCreateErrorException;
import com.gabrielbazante.agendavotingapi.exceptions.generalsErrorExceptions.AgendaAlreadyExistsErrorException;
import com.gabrielbazante.agendavotingapi.exceptions.notFoundExceptions.AgendaNotFoundException;
import com.gabrielbazante.agendavotingapi.exceptions.updateErrorExceptions.AgendaUpdateErrorException;
import com.gabrielbazante.agendavotingapi.repository.AgendaRepository;

@Service
public class AgendaService {
    
    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda findById(Long idAgenda) {
        return agendaRepository.findByIdAgenda(idAgenda);
    }

    public Agenda createAgenda(Agenda agenda) {
        try {
            if (agendaRepository.findByTitle(agenda.getTitle()) != null) {
                throw new AgendaAlreadyExistsErrorException("Agenda already exists");
            }
        } catch (Exception e) {
            throw new AgendaCreateErrorException("Error creating agenda");
        }
        return agendaRepository.save(agenda);
    }

    public Agenda updateAgenda(Long id, Agenda agenda) {
        try {
            if (agendaRepository.findByTitle(agenda.getTitle()) == null) {
                throw new AgendaNotFoundException("Agenda: "+ id +" not found");
            }

            Agenda currentAgenda = agendaRepository.findByIdAgenda(id);
            currentAgenda.setTitle(agenda.getTitle());
            currentAgenda.setDescription(agenda.getDescription());

            return agendaRepository.save(currentAgenda);
        } catch (Exception e) {
            throw new AgendaUpdateErrorException("Error updating agenda");
        }
    }

    public Agenda findByTitle(String title) {
        return agendaRepository.findByTitle(title);
    }

    public void deleteById(Long id) {
        agendaRepository.deleteById(id);
    }
}
