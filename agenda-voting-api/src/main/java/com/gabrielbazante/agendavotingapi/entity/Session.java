package com.gabrielbazante.agendavotingapi.entity;

import java.time.LocalDateTime;

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
@Table(name = "session")
@Data
public class Session {

    @Id
    @SequenceGenerator(name = "session_id_session_seq", sequenceName = "session_id_session_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_id_session_seq")
    @Column(name = "id_session")
    private Long idSession;

    @JoinColumn(referencedColumnName = "id_agenda")
    @OneToOne
    private Agenda idAgenda;

    @Column(name = "starting_vote", nullable = false)
    private LocalDateTime startingVote = LocalDateTime.now();

    @Column(name = "active", nullable = false)
    private String active = "ACTIVE";

    public Session() {
    }

    public Session (Agenda idAgenda) {
        this.idAgenda = idAgenda;
    }
}
