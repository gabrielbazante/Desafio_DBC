package com.gabrielbazante.agendavotingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielbazante.agendavotingapi.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

    Vote findByIdVote(Long idVote);

    @Query("SELECT v FROM Vote v WHERE v.idAgenda.idAgenda = :ID AND v.idVoter.idVoter = :IDVOTER")
    Vote findVoteByIdAgenda(@Param("ID")Long idAgenda, @Param("IDVOTER")Long idVoter);
    
    @Query("SELECT COUNT(v.currentVote) FROM Vote v WHERE v.idAgenda.idAgenda = :idAgenda AND v.currentVote = 'Yes'")
     int countTotalVotesPerAgendaYes(@Param("idAgenda")Long idAgenda);

    @Query("SELECT COUNT(v.currentVote) FROM Vote v WHERE v.idAgenda.idAgenda = :idAgenda AND v.currentVote = 'No'")
     int countTotalVotesPerAgendaNo(@Param("idAgenda")Long idAgenda);


}
