package com.gabrielbazante.agendavotingapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielbazante.agendavotingapi.entity.Voter;
import com.gabrielbazante.agendavotingapi.service.VoterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class VoterControllerTest {

    @Mock
    private VoterService voterService;

    private VoterController voterController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        voterController = new VoterController(voterService);
        mockMvc = MockMvcBuilders.standaloneSetup(voterController).build();
    }

    @Test
    public void testFindByCpf() throws Exception {
        String cpf = "12345678900";
        Voter voter = new Voter();
        voter.setName("John Doe");
        voter.setCpf(cpf);
        when(voterService.findByCpf(eq(cpf))).thenReturn(voter);

        mockMvc.perform(MockMvcRequestBuilders.get("/voters/voter/" + cpf))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(voter)));
    }

    @Test
    public void testCreateVoter() throws Exception {
        Voter voter = new Voter();
        voter.setName("John Doe");
        voter.setCpf("12345678900");
        when(voterService.createVoter(any(Voter.class))).thenReturn(voter);

        mockMvc.perform(MockMvcRequestBuilders.post("/voters/voter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voter)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(voter)));
    }

    @Test
    public void testUpdateVoter() throws Exception {
        Long id = 1L;
        Voter voter = new Voter();
        voter.setName("John Doe");
        voter.setCpf("12345678900");
        when(voterService.updateVoter(eq(id), any(Voter.class))).thenReturn(voter);

        mockMvc.perform(MockMvcRequestBuilders.put("/voters/voter/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voter)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(voter)));
    }

    @Test
    public void testDeleteById() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/voters/voter/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
