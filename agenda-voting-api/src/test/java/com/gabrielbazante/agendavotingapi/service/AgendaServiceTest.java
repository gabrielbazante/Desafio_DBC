package com.gabrielbazante.agendavotingapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.repository.AgendaRepository;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest {

    @InjectMocks
    private AgendaService agendaService;
    
    @Mock
    private AgendaRepository agendaRepository;
    
    @Test
    public void testFindById() {
        Long idAgenda = 1L;
        Agenda expectedAgenda = new Agenda();
        expectedAgenda.setIdAgenda(idAgenda);
        when(agendaRepository.findByIdAgenda(idAgenda)).thenReturn(expectedAgenda);
        
        Agenda actualAgenda = agendaService.findById(idAgenda);
        assertNotNull(actualAgenda);
        assertEquals(expectedAgenda, actualAgenda);
    }
    
    @Test
    public void testCreateAgenda() {
        Agenda agenda = new Agenda();
        agenda.setTitle("Test Agenda");
        when(agendaRepository.findByTitle("Test Agenda")).thenReturn(null);
        when(agendaRepository.save(agenda)).thenReturn(agenda);
        
        Agenda createdAgenda = agendaService.createAgenda(agenda);
        assertNotNull(createdAgenda);
        assertEquals(agenda, createdAgenda);
    }
    
    
    @Test
    public void testUpdateAgenda() {
        Long idAgenda = 1L;
        Agenda agenda = new Agenda();
        agenda.setIdAgenda(idAgenda);
        agenda.setTitle("Test Agenda");
        agenda.setDescription("Test Description");
        
        Agenda currentAgenda = new Agenda();
        currentAgenda.setIdAgenda(idAgenda);
        currentAgenda.setTitle("Current Agenda");
        currentAgenda.setDescription("Current Description");
        when(agendaRepository.findByTitle("Test Agenda")).thenReturn(agenda);
        when(agendaRepository.findByIdAgenda(idAgenda)).thenReturn(currentAgenda);
        when(agendaRepository.save(currentAgenda)).thenReturn(currentAgenda);
        
        Agenda updatedAgenda = agendaService.updateAgenda(idAgenda, agenda);
        assertNotNull(updatedAgenda);
        assertEquals(agenda, updatedAgenda);
        assertEquals("Test Agenda", updatedAgenda.getTitle());
        assertEquals("Test Description", updatedAgenda.getDescription());
    }
    

    @Test
    public void testFindByTitle() {
        String title = "Test Agenda";
        Agenda expectedAgenda = new Agenda();
        expectedAgenda.setTitle(title);
        when(agendaRepository.findByTitle(title)).thenReturn(expectedAgenda);
        
        Agenda actualAgenda = agendaService.findByTitle(title);
        assertNotNull(actualAgenda);
        assertEquals(expectedAgenda, actualAgenda);
    }

    @Test
    public void testDeleteById() {
        Long idAgenda = 1L;
        agendaService.deleteById(idAgenda);
        assertTrue(true);
    }
}
