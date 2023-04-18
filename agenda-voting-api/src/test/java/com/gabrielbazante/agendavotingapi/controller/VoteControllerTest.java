package com.gabrielbazante.agendavotingapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielbazante.agendavotingapi.DTO.VoteDTO;
import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.entity.Vote;
import com.gabrielbazante.agendavotingapi.service.VoteService;

@ExtendWith(MockitoExtension.class)
public class VoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VoteService voteService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        VoteController voteController = new VoteController(voteService);
        mockMvc = MockMvcBuilders.standaloneSetup(voteController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateVote() throws Exception {
        VoteDTO voteDTO = new VoteDTO(1L, "12345678901", "YES");

        Agenda agenda = new Agenda();
        agenda.setIdAgenda(1L);

        Vote vote = new Vote();
        vote.setIdVote(1L);
        vote.setIdAgenda(agenda);
        vote.setCurrentVote(voteDTO.getVote());

        when(voteService.createVote(voteDTO)).thenReturn(vote);

        mockMvc.perform(MockMvcRequestBuilders.post("/votes/voting")
                .content(objectMapper.writeValueAsString(voteDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idVote").value(1));
    }



    @Test
    public void testCountTotalVotesPerAgenda() throws Exception {
        long agendaId = 1L;
        int totalVotesYes = 10;
        int totalVotesNo = 5;

        when(voteService.countTotalVotesPerAgendaYes(agendaId)).thenReturn(totalVotesYes);
        when(voteService.countTotalVotesPerAgendaNo(agendaId)).thenReturn(totalVotesNo);

        mockMvc.perform(get("/votes/count/{id}", agendaId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idAgenda").value(1))
                .andExpect(jsonPath("$.yes").value(10))
                .andExpect(jsonPath("$.no").value(5))
                .andExpect(jsonPath("$.total").value(15));
    }
}
