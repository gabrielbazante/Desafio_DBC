package com.gabrielbazante.agendavotingapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielbazante.agendavotingapi.DTO.SessionDTO;
import com.gabrielbazante.agendavotingapi.entity.Session;
import com.gabrielbazante.agendavotingapi.service.SessionService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    private SessionDTO sessionDTO;

    @BeforeEach
    public void setUp() {
        sessionDTO = new SessionDTO();
        sessionDTO.setIdAgenda(1L);
    }

    @Test
    public void createSession_ReturnsSession() throws Exception {
        Session session = new Session();
        Mockito.when(sessionService.createSession(Mockito.anyLong())).thenReturn(session);

        MvcResult result = mockMvc.perform(post("/sessions/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sessionDTO)))
                .andExpect(status().isOk())
                .andReturn();

        Session returnedSession = objectMapper.readValue(result.getResponse().getContentAsString(), Session.class);
        Assertions.assertEquals(session, returnedSession);
    }

    @Test
    public void createSession() throws Exception {
        mockMvc.perform(post("/sessions/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sessionDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSession_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/sessions/session/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
