package com.gabrielbazante.agendavotingapi.service;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gabrielbazante.agendavotingapi.DTO.VoteDTO;
import com.gabrielbazante.agendavotingapi.entity.Agenda;
import com.gabrielbazante.agendavotingapi.entity.Session;
import com.gabrielbazante.agendavotingapi.entity.Vote;
import com.gabrielbazante.agendavotingapi.entity.Voter;
import com.gabrielbazante.agendavotingapi.repository.SessionRepository;
import com.gabrielbazante.agendavotingapi.repository.VoteRepository;

class VoteServiceTest {
    
    @Mock
    private VoteRepository voteRepository;
    
    @Mock
    private VoterService voterService;
    
    @Mock
    private AgendaService agendaService;
    
    @Mock
    private SessionService sessionService;
    
    @Mock
    private SessionRepository sessionRepository;
    
    @InjectMocks
    private VoteService voteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        voteService = new VoteService(voteRepository, voterService, agendaService, sessionService, sessionRepository);
    }


    @Test
    void testIsVoteInAgenda() {
        // Given
        VoteDTO voteDTO = new VoteDTO(1L, "12345678901", "YES");
        Voter voter = new Voter();
        voter.setIdVoter(1L);
        Mockito.when(voterService.findByCpf(voteDTO.getCpf())).thenReturn(voter);
        Mockito.when(voteRepository.findVoteByIdAgenda(voteDTO.getIdAgenda(), voter.getIdVoter())).thenReturn(new Vote());
        
        // When
        Boolean result = voteService.isVoteInAgenda(voteDTO);
        
        // Then
        assertTrue(result);
    }

    @Test
    void testCountTotalVotesPerAgendaYes() {
        // Given
        Long idAgenda = 1L;
        Mockito.when(voteRepository.countTotalVotesPerAgendaYes(idAgenda)).thenReturn(5);
        
        // When
        int result = voteService.countTotalVotesPerAgendaYes(idAgenda);
        
        // Then
        assertEquals(5, result);
    }

    @Test
    void testCountTotalVotesPerAgendaNo() {
        // Given
        Long idAgenda = 1L;
        Mockito.when(voteRepository.countTotalVotesPerAgendaNo(idAgenda)).thenReturn(3);
        
        // When
        int result = voteService.countTotalVotesPerAgendaNo(idAgenda);
        
        // Then
        assertEquals(3, result);
    }

    @Test
    void testCreateVoteWhenSessionDisabled() {
        // Given
        VoteDTO voteDTO = new VoteDTO(1L, "12345678901", "YES");
        Mockito.when(sessionService.isSessionActive(voteDTO.getIdAgenda())).thenReturn(false);
        
        // When
        Exception exception = assertThrows(RuntimeException.class, () -> {
            voteService.createVote(voteDTO);
        });
        
        // Then
        assertEquals("Session disabled", exception.getMessage());
        Mockito.verify(voteRepository, Mockito.never()).save(Mockito.any(Vote.class));
    }

    @Test
    void testCreateVoteWhenVoteAlreadyExists() {
        // Given
        VoteDTO voteDTO = new VoteDTO(1L, "12345678901", "YES");
        Voter voter = new Voter();
        voter.setIdVoter(1L);
        Mockito.when(voterService.findByCpf(voteDTO.getCpf())).thenReturn(voter);
        Mockito.when(agendaService.findById(voteDTO.getIdAgenda())).thenReturn(new Agenda());
        Mockito.when(sessionRepository.findSessionByIdAgenda(Mockito.any(Agenda.class))).thenReturn(new Session());
        Mockito.when(sessionService.isSessionActive(voteDTO.getIdAgenda())).thenReturn(true);
        Mockito.when(voteRepository.findVoteByIdAgenda(voteDTO.getIdAgenda(), voter.getIdVoter())).thenReturn(new Vote());
        
        // When
        try {
            voteService.createVote(voteDTO);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // Then
            assertEquals("Vote already exists", e.getMessage());
        }
    }    
    
}