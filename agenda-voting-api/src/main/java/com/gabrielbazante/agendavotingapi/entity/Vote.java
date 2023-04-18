package com.gabrielbazante.agendavotingapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vote")
@Data
public class Vote {
    
    @Id
    @SequenceGenerator(name = "vote_id_vote_seq", sequenceName = "vote_id_vote_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "vote_id_vote_seq")
    @Column(name = "id_vote", nullable = false)
    private Long idVote;

    @JoinColumn(referencedColumnName = "id_agenda")
    @OneToOne
    private Agenda idAgenda;

    @JoinColumn(referencedColumnName = "id_voter")
    @OneToOne
    private Voter idVoter;

    @Column(name = "vote", nullable = false)
    private String currentVote;

    public Vote(){}

    public Vote(Voter voter, Agenda agenda, String vote){
        this.idVoter = voter;
        this.idAgenda = agenda;
        this.currentVote = vote;
    }

}
