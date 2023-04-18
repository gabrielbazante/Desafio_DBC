package com.gabrielbazante.agendavotingapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.service.AgendaService;

@ExtendWith(MockitoExtension.class)
public class AgendaControllerTest {

    @InjectMocks
    private AgendaController agendaController;

    @Mock
    private AgendaService agendaService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindById() throws Exception {
        Agenda agenda = new Agenda(1L, "Agenda 1", "Agenda 1 description");
        when(agendaService.findById(1L)).thenReturn(agenda);
        agenda.setTitle("Agenda 1");
        when(agendaService.findById(1L)).thenReturn(agenda);

        mockMvc.perform(get("/agendas/agenda/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.idAgenda").value(1))
            .andExpect(jsonPath("$.title").value("Agenda 1"));
    }

    @Test
    public void testFindByTitle() throws Exception {
        Agenda agenda = new Agenda(1L, "Agenda 1", "Agenda 1 description");
        agenda.setTitle("Agenda 1");
        when(agendaService.findByTitle("Agenda 1")).thenReturn(agenda);

        mockMvc.perform(get("/agendas/agenda/title/Agenda 1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.idAgenda").value(1))
            .andExpect(jsonPath("$.title").value("Agenda 1"));
    }

    @Test
    public void testCreateAgenda() throws Exception {
        Agenda agenda = new Agenda(1L, "Agenda 1", "Agenda 1 description");
        agenda.setTitle("Agenda 1");
        when(agendaService.createAgenda(any(Agenda.class))).thenReturn(agenda);

        mockMvc.perform(post("/agendas/agenda")
                .content(objectMapper.writeValueAsString(agenda))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.idAgenda").value(1))
            .andExpect(jsonPath("$.title").value("Agenda 1"));
    }

    @Test
    public void testUpdateAgenda() throws Exception {
        Agenda agenda = new Agenda(1L, "Agenda 1", "Agenda 1 description");
        agenda.setTitle("Agenda 1");
        when(agendaService.updateAgenda(1L, agenda)).thenReturn(agenda);

        mockMvc.perform(put("/agendas/agenda/1")
               .content(objectMapper.writeValueAsString(
                    agenda))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.idAgenda").value(1))
                    .andExpect(jsonPath("$.title").value("Agenda 1"));
    }
    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/agendas/agenda/1"))
            .andExpect(status().isOk());
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendaController).build();
    }
}