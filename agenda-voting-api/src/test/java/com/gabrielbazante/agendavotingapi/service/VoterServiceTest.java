package com.gabrielbazante.agendavotingapi.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gabrielbazante.agendavotingapi.entity.Voter;
import com.gabrielbazante.agendavotingapi.repository.VoterRepository;

@ExtendWith(MockitoExtension.class)
public class VoterServiceTest {

    @InjectMocks
    private VoterService voterService;

    @Mock
    private VoterRepository voterRepository;


    @Test
    public void testFindById() {
        Long id = 1L;
        Voter voter = new Voter();
        voter.setIdVoter(id);

        when(voterRepository.findByIdVoter(id)).thenReturn(voter);

        Voter result = voterService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getIdVoter());
    }

    @Test
    public void testFindByIdVoter() {
        Long id = 1L;
        Voter voter = new Voter();
        voter.setIdVoter(id);

        when(voterRepository.findByIdVoter(id)).thenReturn(voter);

        Voter result = voterService.findByIdVoter(id);

        assertNotNull(result);
        assertEquals(id, result.getIdVoter());
    }

    @Test
    public void testFindByCpf() {
        String cpf = "12345678901";
        Voter voter = new Voter();
        voter.setCpf(cpf);

        when(voterRepository.findByCpf(cpf)).thenReturn(voter);

        Voter result = voterService.findByCpf(cpf);

        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
    }

    @Test
    public void testCreateVoter() {
        Voter voter = new Voter();
        voter.setCpf("12345678901");

        when(voterRepository.findByCpf(voter.getCpf())).thenReturn(null);
        when(voterRepository.save(voter)).thenReturn(voter);

        Voter result = voterService.createVoter(voter);

        assertNotNull(result);
        assertEquals(voter.getCpf(), result.getCpf());
    }

    @Test
    public void testCreateVoterAlreadyExists() {
        Voter voter = new Voter();
        voter.setCpf("12345678901");

        when(voterRepository.findByCpf(voter.getCpf())).thenReturn(voter);

        assertThrows(RuntimeException.class, () -> {
            voterService.createVoter(voter);
        });
    }

    @Test
    public void testUpdateVoter() {
        Long id = 1L;
        String cpf = "12345678901";
        String name = "John Doe";

        Voter currentVoter = new Voter();
        currentVoter.setIdVoter(id);
        currentVoter.setCpf("98765432109");
        currentVoter.setName("Jane Doe");

        Voter newVoter = new Voter();
        newVoter.setCpf(cpf);
        newVoter.setName(name);

        when(voterRepository.findByCpf(cpf)).thenReturn(currentVoter);
        when(voterRepository.findByIdVoter(id)).thenReturn(currentVoter);
        when(voterRepository.save(currentVoter)).thenReturn(currentVoter);
    
        Voter result = voterService.updateVoter(id, newVoter);
    
        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
        assertEquals(name, result.getName());
    }
    
    @Test
    public void testUpdateVoterNotFound() {
        Long id = 1L;
        Voter voter = new Voter();
        voter.setCpf("123456789");
        voter.setName("John Doe");
    
        when(voterRepository.findByCpf(voter.getCpf())).thenReturn(null);
    
        assertThrows(RuntimeException.class, () -> {
            voterService.updateVoter(id, voter);
        });
    
        verify(voterRepository, times(0)).save(any(Voter.class));
    }
    
    
    @Test
    public void testDeleteById() {
        Long id = 1L;
    
        voterService.deleteById(id);
    
        // verify that the repository's deleteById method was called with the correct id
        verify(voterRepository, times(1)).deleteById(id);
    }
    
    @Test
    public void testDeleteByIdNotFound() {
        Long id = 1L;
        doThrow(new EmptyResultDataAccessException(1)).when(voterRepository).deleteById(id);
        assertThrows(EmptyResultDataAccessException.class, () -> {
            voterService.deleteById(id);
        });

        verify(voterRepository, never()).delete(any(Voter.class));
    }

}    