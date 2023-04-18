package com.gabrielbazante.agendavotingapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.service.AgendaService;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService service) {
        this.agendaService = service;
    }
    
    @GetMapping("/agenda/{id}")
    public Agenda findById(@PathVariable Long id) {
        return agendaService.findById(id);
    }

    @GetMapping("/agenda/title/{title}")
    public Agenda findByTitle(@PathVariable String title) {
        return agendaService.findByTitle(title);
    }

    @PostMapping("/agenda")
    public Agenda createAgenda(@RequestBody Agenda agenda) {
        return agendaService.createAgenda(agenda);
    }

    @PutMapping("/agenda/{id}")
    public Agenda updateAgenda(@PathVariable Long id, @RequestBody Agenda agenda) {
        return agendaService.updateAgenda(id, agenda);
    }

    @DeleteMapping("/agenda/{id}")
    public void deleteById(@PathVariable Long id) {
        agendaService.deleteById(id);
    }

}
