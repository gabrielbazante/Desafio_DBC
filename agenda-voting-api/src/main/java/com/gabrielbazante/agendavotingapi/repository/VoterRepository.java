package com.gabrielbazante.agendavotingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbazante.agendavotingapi.entity.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long>{
    
    Voter findByCpf (String cpf);

    Voter findByIdVoter (Long id);
    
}
