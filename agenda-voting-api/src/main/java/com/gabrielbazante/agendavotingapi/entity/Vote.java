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
    private Long id_vote;

    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @OneToOne
    private Agenda id_agenda;

    @Column(name = "vote", nullable = false)
    private String vote;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "active", nullable = false)
    private String active;
}
