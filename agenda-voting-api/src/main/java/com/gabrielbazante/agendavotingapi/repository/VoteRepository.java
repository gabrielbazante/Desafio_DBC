package com.gabrielbazante.agendavotingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielbazante.agendavotingapi.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

    @Query("SELECT v FROM Vote v WHERE v.cpf = :CPF AND v.active = 'ACTIVE'")
    Vote findByCpf (@Param("CPF")String cpf);
    
    @Query("SELECT v FROM Vote v WHERE v.id_agenda.id_agenda = :ID AND v.active = 'ACTIVE'")
    Vote findByIdVote (@Param("ID")Long idVote);

}
